package com.spring_ecommerce.squid_corals.controllers;


import com.spring_ecommerce.squid_corals.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("getAll")
    ResponseEntity<?> getAllOrders(){
        return ResponseEntity.ok(orderRepository.findAll());
    }


    @GetMapping("deleteAll")
    void deleteAllOrders(){
         orderRepository.deleteAll();
    }
}
