package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.Set;

public interface Store {
    Accident save(Accident accident);

    Collection<Accident> getAllAccidents();

    Collection<AccidentType> getAllTypes();

    Collection<Rule> getAllRules();

    Accident findById(int id);

    Set<Rule> findRules(String[] ids);

    AccidentType findTypeById(int id);


}
