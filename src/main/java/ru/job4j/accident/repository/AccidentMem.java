package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents = new HashMap<>();

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void setAccidents(HashMap<Integer, Accident> accidents) {
        this.accidents = accidents;
    }

    public void create(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }
}
