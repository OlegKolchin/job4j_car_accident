package ru.job4j.accident.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class AccidentJdbcTemplate implements Store {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Accident save(Accident accident) {
        if (accident.getId() == 0) {
            return create(accident);
        }
        return update(accident);
    }

    @Transactional
    Accident create(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement st = connection.prepareStatement(
                    "insert into accident (name, description, address, accident_type_id) values (?, ?, ?, ?)", new String[] {"id" });
            st.setString(1, accident.getName());
            st.setString(2, accident.getDescription());
            st.setString(3, accident.getAddress());
            st.setInt(4, accident.getType().getId());
            return st;
        }, keyHolder);
        updateAccidentRules(accident, keyHolder.getKey().intValue());
        return accident;
    }

    @Transactional
    Accident update(Accident accident) {
        jdbc.update("update accident set name = ?, description = ?, address = ?, accident_type_id = ? where id = ?",
                accident.getName(),
                accident.getDescription(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        deleteRulesByAccidentId(accident.getId());
        updateAccidentRules(accident, accident.getId());
        return accident;
    }

    private void updateAccidentRules(Accident accident, int accidentKey) {
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_rules (accident_id, rule_id) values (?, ?)",
                    accidentKey,
                    rule.getId());
        }
    }

    private void deleteRulesByAccidentId(int id) {
        jdbc.update("delete from accident_rules where accident_id = ?", id);

    }

    @Override
    public List<Accident> getAllAccidents() {
        return jdbc.query("select id, name, description, address, accident_type_id from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setDescription(rs.getString("description"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(findTypeById(rs.getInt("accident_type_id")));
                    accident.setRules(findRulesByAccidentId(accident.getId()));
                    return accident;
                });
    }

    @Override
    public Collection<AccidentType> getAllTypes() {
        return jdbc.query("select id, name from accident_type",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }

    @Override
    public Collection<Rule> getAllRules() {
        return jdbc.query("select id, name from rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    @Override
    public Accident findById(int id) {
        try {
            return jdbc.queryForObject("select id, name, description, address, accident_type_id from accident where id = ?",
                    (rs, row) -> {
                        Accident accident = new Accident();
                        accident.setId(rs.getInt("id"));
                        accident.setName(rs.getString("name"));
                        accident.setDescription(rs.getString("description"));
                        accident.setAddress(rs.getString("address"));
                        accident.setType(findTypeById(rs.getInt("accident_type_id")));
                        accident.setRules(findRulesByAccidentId(accident.getId()));
                        return accident;
                    }, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Set<Rule> findRules(String[] ids) {
        Set<Rule> rules = new HashSet<>();
        Stream.of(ids).mapToInt(Integer::parseInt).forEach(id -> rules.add(findRuleById(id)));
        return rules;
    }

    private Rule findRuleById(int id) {
        return jdbc.queryForObject(
                "select id, name from rules where id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                },
                id);
    }

    private Set<Rule> findRulesByAccidentId(int id) {
        List<Integer> rsl = jdbc.query("select rule_id from accident_rules where accident_id = ?",
                (rs, row) -> rs.getInt("rule_id"), id);
        return rsl.stream().map(this::findRuleById).collect(Collectors.toSet());
    }

    @Override
    public AccidentType findTypeById(int id) {
        return jdbc.queryForObject(
                "select id, name from accident_type where id = ?",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                },
                id);
    }
}