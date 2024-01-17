package org.learning.springlibrary.controller;

import org.learning.springlibrary.repository.OfferteRepository;
import org.learning.springlibrary.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offertespeciali")
public class OfferteController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OfferteRepository offerteRepository;

}
