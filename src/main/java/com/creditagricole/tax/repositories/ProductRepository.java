package com.creditagricole.tax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creditagricole.tax.entities.Product;

/**
 * The Interface ProductRepository.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
