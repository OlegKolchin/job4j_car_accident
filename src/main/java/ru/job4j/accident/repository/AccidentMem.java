package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents = new HashMap<>();

    public HashMap<Integer, Accident> getAccidents() {
        return accidents;
    }

    public void setAccidents(HashMap<Integer, Accident> accidents) {
        this.accidents = accidents;
    }

    public void init() {
        Accident accident = new Accident(1, "Accident", "Text", "Address");
        for (int i = 1; i <= 5; i++) {
            accidents.put(i, accident);
        }
    }
}
