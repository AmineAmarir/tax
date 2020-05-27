package com.creditagricole.tax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creditagricole.tax.entities.Order;

/**
 * The Interface OrderRepository.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
