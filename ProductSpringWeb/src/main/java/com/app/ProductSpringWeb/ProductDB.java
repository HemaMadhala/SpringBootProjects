package com.app.ProductSpringWeb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDB extends JpaRepository<Product, Integer> {
    Product findByName(String name);

    Product findAllByName(String name);

    Product findByPlace(String place);

    Product deleteByName(String name);

    Product save(Product p);

}
