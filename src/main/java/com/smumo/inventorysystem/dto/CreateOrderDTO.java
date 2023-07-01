package com.smumo.inventorysystem.dto;

import com.smumo.inventorysystem.entities.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {
    private long customerId;
    private List<OrderItems> orderItemsList;
}
