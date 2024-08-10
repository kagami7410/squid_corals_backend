package com.spring_ecommerce.squid_corals.controllers;

import com.spring_ecommerce.squid_corals.repository.BasketItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basketItems")
public class BasketItemController {

    @Autowired
    BasketItemRepository basketItemRepository;

    @GetMapping("/getAll")
    ResponseEntity<?> getAllBasketItems(){
        return ResponseEntity.ok(basketItemRepository.findAll());
    }


    @DeleteMapping("/deleteAll")
    private String deleteAllUsers(){
        basketItemRepository.deleteAll();
        return "all users deleted";
    }
}
