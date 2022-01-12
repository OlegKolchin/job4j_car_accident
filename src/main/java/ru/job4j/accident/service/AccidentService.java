package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Stream;

@Service
public class AccidentService {

    private AccidentRepository repository;
    private RuleRepository ruleRepository;
    private AccidentTypeRepository accidentTypeRepository;

    public AccidentService(AccidentRepository repository, RuleRepository ruleRepository, AccidentTypeRepository accidentTypeRepository) {
        this.repository = repository;
        this.ruleRepository = ruleRepository;
        this.accidentTypeRepository = accidentTypeRepository;
    }

    @Transactional
    public Accident save(Accident accident, String typeId, String[] rIds) {
        accident.setType(findTypeById(Integer.parseInt(typeId)));
        accident.setRules(findRules(rIds));
        repository.save(accident);
        return accident;
    }

    public Collection<Accident> getAccidents() {
        List<Accident> rsl = new ArrayList<>();
        repository.findAll().forEach(rsl :: add);
        return rsl;
    }

    public Collection<AccidentType> getAccidentTypes() {
        List<AccidentType> rsl = new ArrayList<>();
        accidentTypeRepository.findAll().forEach(rsl :: add);
        return rsl;
    }

    public Collection<Rule> getRules() {
        List<Rule> rsl = new ArrayList<>();
        ruleRepository.findAll().forEach(rsl :: add);
        return rsl;
    }

    public Accident findById(int id) {
        return repository.findById(id).get();
    }

    public AccidentType findTypeById(int id) {
        return accidentTypeRepository.findById(id).get();
    }

    public Set<Rule> findRules(String[] ids) {
        Set<Rule> rules = new HashSet<>();
        Stream.of(ids).mapToInt(Integer::parseInt).forEach(id -> rules.add(ruleRepository.findById(id).get()));
        return rules;
    }

}
