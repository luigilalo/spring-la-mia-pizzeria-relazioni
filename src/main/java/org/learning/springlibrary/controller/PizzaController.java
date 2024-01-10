package org.learning.springlibrary.controller;

import org.learning.springlibrary.model.Pizza;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/crea")
public class PizzaController {
    @GetMapping
    public String formPizza (Model model) {
        model.addAttribute("pizza", new Pizza());
        return "crea";
    }
    @PostMapping("/creaPizza")
    public String creaPizza(@ModelAttribute Pizza pizza, Model model) {
        return "redirect:/home";
    }
}
