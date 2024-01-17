package org.learning.springlibrary.controller;

import org.learning.springlibrary.model.Offerte;
import org.learning.springlibrary.model.Pizza;
import org.learning.springlibrary.repository.OfferteRepository;
import org.learning.springlibrary.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/offertespeciali")
public class OfferteController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OfferteRepository offerteRepository;

    @GetMapping("/crea")
    public String creaOfferta(@RequestParam(name = "pizzaId", required = true) Integer pizzaId,
                         Model model) {
        // verifico se il book esiste su database
        Optional<Pizza> result = pizzaRepository.findById(pizzaId);
        if (result.isPresent()) {
            // estraggo il Book dall'Optional
            Pizza offertaPizza = result.get();
            // passo al Model il Book come attributo
            model.addAttribute("pizza", offertaPizza);
            Offerte nuovaOfferta = new Offerte();
            nuovaOfferta.setPizza(offertaPizza);
            nuovaOfferta.setStartDate(LocalDate.now());
            nuovaOfferta.setExpireDate(LocalDate.now().plusDays(7));
            model.addAttribute("offerta", nuovaOfferta);
            // restituisco il template
            return "/creaOfferta";
        } else {
            // se l'Optional è vuoto sollevo una eccezione HTTP 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    " " +  pizzaId + " not found");
        }
    }


}
