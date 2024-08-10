package com.spring_ecommerce.squid_corals.controllers;


import com.spring_ecommerce.squid_corals.models.Item;
import com.spring_ecommerce.squid_corals.models.User;
import com.spring_ecommerce.squid_corals.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/getAll")
    public ResponseEntity<List<Item>> getAllItems(){
        return ResponseEntity.ok(itemRepository.findAll());
    }

    @DeleteMapping("/deleteAll")
    private String deleteAllUsers(){
        itemRepository.deleteAll();
        return "all users deleted";
    }


    @PostMapping("/add")
    public ResponseEntity<String> addItem (@RequestBody Item newItem){
        boolean isItemAlreadyAdded = itemRepository.findAll().stream()
                        .anyMatch(item -> item.getTitle().equalsIgnoreCase(newItem.getTitle()));
        if(!isItemAlreadyAdded){
            itemRepository.save(newItem);
            return ResponseEntity.ok(newItem.getTitle() +" is added!");
        }
        else {
            return ResponseEntity.ok(newItem.getTitle() +" is already in the database");
        }
    }
}
