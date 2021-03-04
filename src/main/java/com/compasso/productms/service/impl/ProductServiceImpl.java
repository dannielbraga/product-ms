package com.compasso.productms.service.impl;

import com.compasso.productms.domain.entity.Product;
import com.compasso.productms.domain.repository.ProductRepository;
import com.compasso.productms.domain.repository.specification.ProductMaxPriceSpecification;
import com.compasso.productms.domain.repository.specification.ProductMinPriceSpecification;
import com.compasso.productms.domain.repository.specification.ProductNameDescriptionSpecification;
import com.compasso.productms.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void saveProduct(Product product) {
        this.productRepository.save(product);
    }

    @Override
    @Transactional
    public void updateProduct(Integer id, Product product) {
        this.productRepository
                .findById(id)
                .map(productExisting -> {
                    product.setId(productExisting.getId());
                    this.productRepository.save(product);
                    return productExisting;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @Override
    public Optional<Product> getProduct(Integer id) {
        return this.productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteProduct(Product product) {
        this.productRepository.delete(product);
    }

    @Override
    public List<Product> search(String q, BigDecimal min_price, BigDecimal max_price) {
        Specification<Product> specification = Specification
                .where(new ProductNameDescriptionSpecification(q))
                .and(new ProductMinPriceSpecification(min_price))
                .and(new ProductMaxPriceSpecification(max_price));
        return productRepository.findAll(specification);
    }
}
