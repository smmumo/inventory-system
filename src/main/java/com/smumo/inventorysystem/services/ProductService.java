package com.smumo.inventorysystem.services;

import com.smumo.inventorysystem.dto.CreateProductDto;
import com.smumo.inventorysystem.dto.ProductDto;
import com.smumo.inventorysystem.dto.UpdateProductDto;
import com.smumo.inventorysystem.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public List<ProductDto> getProducts() ;
    public Product CreateProduct(CreateProductDto productDto);
    public ProductDto getProduct(Long Id) ;
    public void UpdateProduct(long id , UpdateProductDto productDto);

    void deleteByID(long id);
}
