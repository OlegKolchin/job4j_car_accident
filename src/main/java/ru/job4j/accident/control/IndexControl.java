package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.repository.AccidentMem;

import java.util.ArrayList;


@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        AccidentMem mem = new AccidentMem();
        mem.init();
        model.addAttribute("accidents", new ArrayList<>(mem.getAccidents().values()));
        return "index";
    }
}