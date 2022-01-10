package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.Store;

import java.util.Collection;
import java.util.Set;

@Service
public class AccidentService {
    private Store store;

    public AccidentService(Store store) {
        this.store = store;
    }

    public Accident save(Accident accident) {
        return store.save(accident);
    }

    public Collection<Accident> getAccidents() {
        return store.getAllAccidents();
    }

    public Collection<AccidentType> getAccidentTypes() {
        return store.getAllTypes();
    }

    public Collection<Rule> getRules() {
        return store.getAllRules();
    }

    public Accident findById(int id) {
        return store.findById(id);
    }

    public AccidentType findTypeById(int id) {
        return store.findTypeById(id);
    }

    public Set<Rule> findRules(String[] ids) {
        return store.findRules(ids);
    }

}
