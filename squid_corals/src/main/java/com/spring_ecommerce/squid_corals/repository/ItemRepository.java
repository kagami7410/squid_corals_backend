package com.spring_ecommerce.squid_corals.repository;

import com.spring_ecommerce.squid_corals.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByTitle(String title);

}
