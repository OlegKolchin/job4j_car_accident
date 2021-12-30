package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents;

    public AccidentMem(HashMap<Integer, Accident> accidents) {
        for (int i = 1; i <= 5; i++) {
            accidents.put(i, new Accident(i, "Accident", "Text", "Address"));
        }
        this.accidents = accidents;
    }

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
