package com.compasso.productms.rest.controller;

import com.compasso.productms.domain.entity.Product;
import com.compasso.productms.service.IProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService produtoService;

    @CrossOrigin
    @ApiOperation(value = "Cria um novo produto na base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna 201 quando o produto foi criado com sucesso."),
            @ApiResponse(code = 400, message = "Retorna 400 quando ocorrer algum erro de validação do domínio.")
    })
    @PostMapping
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product) {
        this.produtoService.saveProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @CrossOrigin
    @ApiOperation(value = "Atualiza os dados de um produto na base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna 200 quando o produto foi atualizado com sucesso."),
            @ApiResponse(code = 400, message = "Retorna 400 quando ocorrer algum erro de validação do domínio."),
            @ApiResponse(code = 404, message = "Retorna 404 quando o produto que se deseja atualizar não existe na base de dados.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @Valid @RequestBody Product product) {
        this.produtoService.updateProduct(id, product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Recupera os dados de um produto filtrando pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna 200 quando o produto foi localizado com sucesso."),
            @ApiResponse(code = 404, message = "Retorna 404 quando o produto que se deseja buscar não existe na base de dados.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
        Optional<Product> product = this.produtoService.getProduct(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin
    @ApiOperation(value = "Recupera os dados de todos os produtos na base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna 200 quando os produtos foram localizados com sucesso (mesmo não havendo produtos).")
    })
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = this.produtoService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation(value = "Delete um produto na base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna 200 quando o produto foi deletado com sucesso."),
            @ApiResponse(code = 404, message = "Retorna 404 quando o produto que se deseja deletar não existe na base de dados")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) {
        Optional<Product> product = this.produtoService.getProduct(id);
        return product.map(value -> {
            this.produtoService.deleteProduct(value);
            return new ResponseEntity<>(HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin
    @ApiOperation(value = "Busca produtos na base de dados filtrando pelos campos name e description, e pelo intervalo de valor aceitável no campo price.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna 200 quandos os produtos forem filtrados com sucesso (tendo ou não produtos retornados).")
    })
    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(@RequestParam(required = false) String q,
                                                @RequestParam(required = false) BigDecimal min_price,
                                                @RequestParam(required = false) BigDecimal max_price) {
        List<Product> products = this.produtoService.search(q, min_price, max_price);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}



