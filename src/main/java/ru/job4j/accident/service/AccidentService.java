package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;
import java.util.HashMap;

@Service
public class AccidentService {
    private AccidentMem store;

    public AccidentService(AccidentMem store) {
        this.store = store;
    }

    public void create(Accident accident) {
        store.create(accident);
    }

    public Collection<Accident> getAccidents() {
        return store.getAccidents();
    }

    public Collection<AccidentType> getAccidentTypes() {
        return store.getTypes();
    }

    public Accident findById(int id) {
        return store.findById(id);
    }

    public AccidentType findTypeById(int id) {
        return store.findTypeById(id);
    }
}
