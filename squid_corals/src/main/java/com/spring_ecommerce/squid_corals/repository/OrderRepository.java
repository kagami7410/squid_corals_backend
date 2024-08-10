package com.spring_ecommerce.squid_corals.repository;

import com.spring_ecommerce.squid_corals.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
