package com.smumo.inventorysystem.dto;

import com.smumo.inventorysystem.entities.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class CreateOrderDTO {
    private long customerId;
    private List<OrderItems> orderItemsList;
    public  CreateOrderDTO(){}

    public CreateOrderDTO(long customerId, List<OrderItems> orderItemsList) {
        this.customerId = customerId;
        this.orderItemsList = orderItemsList;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public List<OrderItems> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItems> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }
}
