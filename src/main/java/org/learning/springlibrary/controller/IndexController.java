package org.learning.springlibrary.controller;

import jakarta.validation.Valid;
import org.learning.springlibrary.model.Pizza;
import org.learning.springlibrary.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with id " + id + " not found");
        }
    }
    // metodo che restituisce la pagina di modifica del Book
    @GetMapping("/modifica/{id}")
    public String modificaPizza(@PathVariable Integer id, Model model) {
        // recupero il libro da database
        Optional<Pizza> result = pizzaRepository.findById(id);
        // verifico se il Book è presente
        if (result.isPresent()) {
            // lo passo come attributo del Model
            model.addAttribute("pizza", result.get());
            // ritorno il template
            return "/modifica";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found");
        }
    }
    // metodo che riceve il submit del form di edit
    @PostMapping("/modifica/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza ,
                         BindingResult bindingResult) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            Pizza pizzaModificata = result.get();
            // valido i dati del libro
            if (bindingResult.hasErrors()) {
                // se ci sono errori di validazione
                return "/modifica";
            }
            // se sono validi salvo il libro su db

            Pizza savedBook = pizzaRepository.save(formPizza);
            // faccio la redirect alla pagina di dettaglio del libro
            return "redirect:/" + id;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PIZZA WITH" + id + " not found");
        }
    }

}