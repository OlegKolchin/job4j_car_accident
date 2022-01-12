package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

public class AccidentHibernate implements Store {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Accident save(Accident accident) {
        tx(session -> {
            session.saveOrUpdate(accident);
            return accident;
        });
        return null;
    }

    @Override
    public Collection<Accident> getAllAccidents() {
        return tx(session -> session.createQuery("from Accident a join fetch a.rules").list());
    }

    @Override
    public Collection<AccidentType> getAllTypes() {
        return tx(session -> session.createQuery("from AccidentType").list());
    }

    @Override
    public Collection<Rule> getAllRules() {
        return tx(session -> session.createQuery("from Rule").list());
    }

    @Override
    public Accident findById(int id) {
        return (Accident) tx(session -> session.createQuery("from Accident a where a.id = :a_id")
                .setParameter("a_id", id).uniqueResult());
    }

    @Override
    public Set<Rule> findRules(String[] ids) {
        Set<Rule> rules = new HashSet<>();
        Stream.of(ids).mapToInt(Integer::parseInt).forEach(id -> rules.add(findRuleById(id)));
        return rules;
    }

    private Rule findRuleById(int id) {
        return (Rule) tx(session -> session.createQuery("from Rule r where r.id = :r_id")
                .setParameter("r_id", id).uniqueResult());
    }

    @Override
    public AccidentType findTypeById(int id) {
        return (AccidentType) tx(session -> session.createQuery("from AccidentType at where at.id = :at_id")
                .setParameter("at_id", id).uniqueResult());
    }

    private <T> T tx(final Function<Session, T> command) {
        Session session = sf.openSession();
        try {
            T rsl = command.apply(session);
            session.beginTransaction().commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
