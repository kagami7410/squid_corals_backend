package com.spring_ecommerce.squid_corals.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="items")
@JsonIgnoreProperties({"basketItems"})
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String title;

    @Column
    String description;

    @Column
    String code;

    @Column
    Float price;

    @Column
    CoralType coralType;

//    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
//    @JsonIgnoreProperties({"item"})
//    private Quantity quantity;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"item"})
    private Set<BasketItem> basketItems = new HashSet<>();



}
