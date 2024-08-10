package com.spring_ecommerce.squid_corals.controllers;

import com.spring_ecommerce.squid_corals.models.Basket;
import com.spring_ecommerce.squid_corals.repository.BasketItemRepository;
import com.spring_ecommerce.squid_corals.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/baskets")
public class BasketController {

    @Autowired
    BasketItemRepository basketItemRepository;

    @Autowired
    BasketRepository basketRepository;

    @GetMapping("/getAll")
    List<Basket> getAllBasket(){
        return basketRepository.findAll();
    }
}
