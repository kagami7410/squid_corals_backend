package com.spring_ecommerce.squid_corals.repository;

import com.spring_ecommerce.squid_corals.models.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
}
