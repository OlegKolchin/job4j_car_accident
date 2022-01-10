package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.stream.Stream;

public class AccidentMem {
    private HashMap<Integer, Accident> accidents = new HashMap<>();

    private HashMap<Integer, AccidentType> types = new HashMap<>();

    private Set<Rule> rules = new HashSet<>();

    public AccidentMem() {
        initRules();
        initTypes();
        for (int i = 1; i <= 5; i++) {
            accidents.put(i, new Accident(i, "Accident", "Text",
                    "Address", getTypeById(1), rules));
        }
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public Collection<AccidentType> getTypes() {
        return types.values();
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public AccidentType findTypeById(int id) {
        return types.get(id);
    }

    private void initTypes() {
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));
    }

    private void initRules() {
        rules.add(Rule.of(1, "Статья. 1"));
        rules.add(Rule.of(2, "Статья. 2"));
        rules.add(Rule.of(3, "Статья. 3"));
    }


    public void create(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public void createType(AccidentType type) {
        types.put(type.getId(), type);
    }

    public AccidentType getTypeById(int id) {
        return types.get(id);
    }

    private Rule findRuleById(int id) {
        return rules.stream().filter(rule -> rule.getId() == id).findFirst().get();
    }

    public Set<Rule> findRules(String[] ids) {
        Set<Rule> rules = new HashSet<>();
        Stream.of(ids).mapToInt(Integer::parseInt).forEach(id -> rules.add(findRuleById(id)));
        return rules;
    }
}
