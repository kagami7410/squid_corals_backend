package com.spring_ecommerce.squid_corals.repository;

import com.spring_ecommerce.squid_corals.models.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
}
