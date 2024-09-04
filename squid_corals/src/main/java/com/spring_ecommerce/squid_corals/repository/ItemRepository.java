package com.spring_ecommerce.squid_corals.repository;

import com.spring_ecommerce.squid_corals.models.CoralType;
import com.spring_ecommerce.squid_corals.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByTitle(String title);
    List<Item> findByCoralType(CoralType coralType);

}
