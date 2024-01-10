package org.learning.springlibrary.controller;

import jakarta.validation.Valid;
import org.learning.springlibrary.model.Pizza;
import org.learning.springlibrary.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/crea")

public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;
    @GetMapping
    public String formPizza (Model model) {
        model.addAttribute("pizza", new Pizza());
        return "crea";
    }
    @PostMapping("/creaPizza")
    public String creaPizza(@Valid @ModelAttribute("pizza") Pizza pizza, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/crea";
        }
        Pizza savedPizza = pizzaRepository.save(pizza);
        return "redirect:/" + pizza.getId();
    }
}
