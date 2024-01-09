package org.learning.springlibrary.repository;

import org.learning.springlibrary.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

}
