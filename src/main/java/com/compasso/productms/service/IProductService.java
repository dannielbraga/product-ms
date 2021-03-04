package com.compasso.productms.service;

import com.compasso.productms.domain.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    void saveProduct(Product product);

    void updateProduct(Integer id, Product product);

    Optional<Product> getProduct(Integer id);

    List<Product> getAllProducts();

    void deleteProduct(Product product);

    List<Product> search(String q, BigDecimal min_price, BigDecimal max_price);
}
