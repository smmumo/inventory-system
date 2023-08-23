package com.smumo.inventorysystem.dto;

import com.smumo.inventorysystem.entities.OrderItems;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private long customerId;
    private String status;
    private List<OrderItems> orderItemsList;

    public OrderDTO(Long id, long customerId, String status, List<OrderItems> orderItemsList) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.orderItemsList = orderItemsList;
    }

    public OrderDTO(){}
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
