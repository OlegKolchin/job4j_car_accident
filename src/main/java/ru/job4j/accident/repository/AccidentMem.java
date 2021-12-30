package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class AccidentMem {
    private HashMap<Integer, Accident> accidents;

    private HashMap<Integer, AccidentType> types = new HashMap<>();

    public AccidentMem(HashMap<Integer, Accident> accidents) {
        initTypes();
        for (int i = 1; i <= 5; i++) {
            accidents.put(i, new Accident(i, "Accident", "Text",
                    "Address", getTypeById(1)));
        }
        this.accidents = accidents;
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void setAccidents(HashMap<Integer, Accident> accidents) {
        this.accidents = accidents;
    }

    public Collection<AccidentType> getTypes() {
        return types.values();
    }

    public AccidentType findTypeById(int id) {
        return types.get(id);
    }

    private void initTypes() {
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));
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
}
