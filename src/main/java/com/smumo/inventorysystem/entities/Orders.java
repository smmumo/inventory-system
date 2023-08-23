package com.smumo.inventorysystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue
    private Long id;
    //private int qty;
    private long customerId;
    //private Long productId;
    private String status;
    //private  String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItems> orderItemsList;
    public Orders(){}
    public Orders(Long id, long customerId, String status, List<OrderItems> orderItemsList) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.orderItemsList = orderItemsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItems> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItems> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }
}