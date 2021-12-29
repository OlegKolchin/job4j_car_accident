package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.service.AccidentService;

import java.util.ArrayList;


@Controller
public class IndexControl {
    private AccidentMem store;

    public IndexControl(AccidentMem store) {
        Accident accident = new Accident(1, "Accident", "Text", "Address");
        for (int i = 1; i <= 5; i++) {
            store.addAccident(i, accident);
        }
        this.store = store;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", new AccidentService().getAccidents(store));
        return "index";
    }
}