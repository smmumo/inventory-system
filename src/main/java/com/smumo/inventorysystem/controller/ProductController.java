package com.smumo.inventorysystem.controller;

import com.smumo.inventorysystem.dto.CreateProductDto;
import com.smumo.inventorysystem.dto.ProductDto;
import com.smumo.inventorysystem.dto.UpdateProductDto;
import com.smumo.inventorysystem.entities.Product;
import com.smumo.inventorysystem.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private  final ProductService productService ;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<ProductDto> productList(){
        List<ProductDto> product = productService.getProducts();
        return product;
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable long id){
        return productService.getProduct(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id){
        productService.deleteByID(id);
    }

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductDto createProductDto){
        Product savedProduct = productService.CreateProduct(createProductDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody UpdateProductDto product,
                                                @PathVariable long id) {
        productService.UpdateProduct(id,product);
        return ResponseEntity.noContent().build();
    }
}
