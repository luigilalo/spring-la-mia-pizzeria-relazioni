package org.learning.springlibrary.controller;

import org.learning.springlibrary.model.Pizza;
import org.learning.springlibrary.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model) {

        List<Pizza> pizzaList = pizzaRepository.findAll();

        model.addAttribute("pizzaList", pizzaList);
        return "home";

    }

    @GetMapping ("/{id}")
    public String details(@PathVariable Integer id, Model model) {
        // nel corpo del metodo ho l'id del book da cercare
        Optional<Pizza> result = pizzaRepository.findById(id);
        // verifico se il Book è stato trovato
        if (result.isPresent()) {
            // estraggo il Book dall'Optional
            Pizza pizza = result.get();
            // aggiungo al Model l'attributo con il Book
            model.addAttribute("pizza", pizza);
            // restituisco il template
            return "details";
        } else {
            // gestisco il caso in cui nel database un Book con quell'id non c'è
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + id + " not found");
        }
    }


}
