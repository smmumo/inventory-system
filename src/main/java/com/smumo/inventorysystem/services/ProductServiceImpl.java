package com.smumo.inventorysystem.services;

import com.smumo.inventorysystem.dto.CreateProductDto;
import com.smumo.inventorysystem.dto.ProductDto;
import com.smumo.inventorysystem.dto.UpdateProductDto;
import com.smumo.inventorysystem.entities.Product;
import com.smumo.inventorysystem.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class ProductServiceImpl implements  ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public List<ProductDto> getProducts() {
        var productDtoslist = productRepository.findAll().stream()
                .map( product -> new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice()
                )).collect(Collectors.toList());
        return productDtoslist;
    }

    @Override
    public Product CreateProduct(CreateProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(product.getPrice());
        product.setQuantity(2);
        product.setProductCode(UUID.randomUUID().toString());
        productRepository.save(product);
        log.info("Product {} saved ",product.getId());
        return product;
    }

    @Override
    public ProductDto getProduct(Long Id) {

        var product = productRepository.findById(Id);
        ProductDto productDto = new ProductDto();
        if(product.isPresent()){
            productDto.setPrice(product.get().getPrice());
            productDto.setPrice(product.get().getPrice());
        }else{
             //throw new StudentNotFoundException("id-" + id);
        }
        return productDto;
    }

    @Override
    public void UpdateProduct(long id, UpdateProductDto productDto) {

        Optional<Product> productOptional = productRepository.findById(id);
        Product updateProd = new Product();

        if(productOptional.isEmpty()){
            // throw new StudentNotFoundException("id-" + id);
        }
        updateProd.setId(id);
        updateProd.setPrice(productDto.getPrice());
        updateProd.setName(productDto.getName());

        productRepository.save(updateProd);
    }

    @Override
    public void deleteByID(long id) {
        productRepository.deleteById(id);
    }
}
