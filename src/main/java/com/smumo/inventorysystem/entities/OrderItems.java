package com.smumo.inventorysystem.entities;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private BigDecimal price;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;
    public OrderItems(){}
    public OrderItems(Long id, Integer quantity, BigDecimal price, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
