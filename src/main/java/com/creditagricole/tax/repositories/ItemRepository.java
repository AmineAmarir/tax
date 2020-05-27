package com.creditagricole.tax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creditagricole.tax.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
}
