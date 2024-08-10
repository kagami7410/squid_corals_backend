package com.spring_ecommerce.squid_corals.services;

import com.spring_ecommerce.squid_corals.models.Basket;
import com.spring_ecommerce.squid_corals.models.BasketItem;
import com.spring_ecommerce.squid_corals.models.Order;
import com.spring_ecommerce.squid_corals.models.User;
import com.spring_ecommerce.squid_corals.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public void submitOrder(User user, Set<BasketItem> basketItems){
        Order order = new Order();
        order.setUser(user);
        order.setBasketItems(basketItems);
        orderRepository.save(order);
    }
}
