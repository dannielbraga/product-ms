package com.compasso.productms.controller;

import com.compasso.productms.domain.entity.Product;
import com.compasso.productms.rest.controller.ProductController;
import com.compasso.productms.service.IProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Test
    void saveProductTest(){
        // Arrange
        Product product = new Product();
        IProductService iProductService = Mockito.mock(IProductService.class);
        this.productController = new ProductController(iProductService);

        // Action
        ResponseEntity<Product> httpStatus = this.productController.saveProduct(product);

        // Assert
        assertEquals(HttpStatus.CREATED, httpStatus.getStatusCode());
    }

    @Test
    void updateProductTest(){
        // Arrange
        Product product = new Product();
        IProductService iProductService = Mockito.mock(IProductService.class);
        this.productController = new ProductController(iProductService);
        Integer id = 1;

        // Action
        ResponseEntity<Product> httpStatus = this.productController.updateProduct(id, product);

        // Assert
        assertEquals(HttpStatus.OK, httpStatus.getStatusCode());
    }

    @Test
    void getProductTest(){
        // Arrange
        Integer id = 1;
        Product product = new Product();
        Optional<Product> optionalProduct = Optional.of(product);
        IProductService iProductService = Mockito.mock(IProductService.class);
        this.productController = new ProductController(iProductService);
        Mockito.doReturn(optionalProduct).when(iProductService).getProduct(id);

        // Action
        ResponseEntity<Product> httpStatus = this.productController.getProduct(id);

        // Assert
        assertEquals(HttpStatus.OK, httpStatus.getStatusCode());
    }

    @Test
    void getProductsTest(){
        // Arrange
        Product product = new Product();
        List<Product> products = new ArrayList<>();
        products.add(product);
        IProductService iProductService = Mockito.mock(IProductService.class);
        this.productController = new ProductController(iProductService);
        Mockito.doReturn(products).when(iProductService).getAllProducts();

        // Action
        ResponseEntity<List<Product>> httpStatus = this.productController.getProducts();

        // Assert
        assertEquals(HttpStatus.OK, httpStatus.getStatusCode());
    }

    @Test
    void deleteProductTest(){
        // Arrange
        Integer id = 1;
        Product product = new Product();
        Optional<Product> optionalProduct = Optional.of(product);
        IProductService iProductService = Mockito.mock(IProductService.class);
        this.productController = new ProductController(iProductService);
        Mockito.doReturn(optionalProduct).when(iProductService).getProduct(id);
        Mockito.doNothing().when(iProductService).deleteProduct(product);

        // Action
        ResponseEntity<?> httpStatus = this.productController.deleteProduct(id);

        // Assert
        assertEquals(HttpStatus.OK, httpStatus.getStatusCode());
    }

    @Test
    void searchTest(){
        // Arrange
        IProductService iProductService = Mockito.mock(IProductService.class);
        this.productController = new ProductController(iProductService);
        Product product = new Product();
        List<Product> products = new ArrayList<>();
        products.add(product);
        String q = "a";
        BigDecimal min_price = new BigDecimal("10");
        BigDecimal max_price = new BigDecimal("20");
        Mockito.doReturn(products).when(iProductService).search(q, min_price, max_price);

        // Action
        ResponseEntity<List<Product>> httpStatus = this.productController.search(q, min_price, max_price);

        // Assert
        assertEquals(HttpStatus.OK, httpStatus.getStatusCode());
    }
}
