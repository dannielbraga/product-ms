package com.compasso.productms.service;

import com.compasso.productms.domain.entity.Product;
import com.compasso.productms.domain.repository.ProductRepository;
import com.compasso.productms.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void saveProductTest(){
        // Arrange
        Product product = new Product();
        product.setId(1);
        product.setName("arroz");
        product.setDescription("arroz tipo 1 tio jorge");
        product.setPrice(new BigDecimal("8.99"));

        // Action
        this.productService.saveProduct(product);

        // Assert
        Mockito.verify(this.productRepository, Mockito.times(1)).save(product);
    }

    @Test
    void updateProductTest(){
        // Arrange
        Integer id = 1;

        Product product = new Product();
        product.setId(id);
        product.setName("arroz");
        product.setDescription("arroz tipo 1 tio jorge");
        product.setPrice(new BigDecimal("8.99"));
        Optional<Product> optionalProduct = Optional.of(product);

        Mockito.doReturn(optionalProduct).when(this.productRepository).findById(id);

        // Action
        this.productService.updateProduct(id, product);

        // Assert
        Mockito.verify(this.productRepository, Mockito.times(1)).save(product);
    }

    @Test
    void getProductTest(){
        // Arrange
        Integer id = 1;

        Product product = new Product();
        product.setId(id);
        product.setName("arroz");
        product.setDescription("arroz tipo 1 tio jorge");
        product.setPrice(new BigDecimal("8.99"));
        Optional<Product> optionalProduct = Optional.of(product);

        Mockito.doReturn(optionalProduct).when(this.productRepository).findById(id);

        // Action
        Optional<Product> optionalProduct1 = this.productService.getProduct(id);

        // Assert
        assertEquals(optionalProduct, optionalProduct1);
    }

    @Test
    void getAllProductsTest(){
        // Arrange
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("arroz");
        product1.setDescription("arroz tipo 1 tio jorge");
        product1.setPrice(new BigDecimal("8.99"));

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("feijao");
        product2.setDescription("feijao carioca tipo 1 tia maria");
        product2.setPrice(new BigDecimal("6.56"));

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Mockito.doReturn(products).when(this.productRepository).findAll();

        // Action
        List<Product> productList = this.productService.getAllProducts();

        // Assert
        assertEquals(products, productList);
    }

    @Test
    void deleteProductTest(){
        // Arrange
        Product product = new Product();
        product.setId(1);
        product.setName("arroz");
        product.setDescription("arroz tipo 1 tio jorge");
        product.setPrice(new BigDecimal("8.99"));

        // Action
        this.productService.deleteProduct(product);

        // Assert
        Mockito.verify(this.productRepository, Mockito.times(1)).delete(product);
    }
}
