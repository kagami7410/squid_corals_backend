package com.spring_ecommerce.squid_corals.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="basket_items")
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"basket","user","basketItems"})
    private Basket basket;


    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"item", "basketItems"})
    private Item item;


    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"item"})
    private Order order;

    private Integer quantity;
}
