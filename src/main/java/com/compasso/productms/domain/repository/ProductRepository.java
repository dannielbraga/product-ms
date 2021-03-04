package com.compasso.productms.domain.repository;

import com.compasso.productms.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, Integer>,
                                           JpaSpecificationExecutor<Product> {
}
