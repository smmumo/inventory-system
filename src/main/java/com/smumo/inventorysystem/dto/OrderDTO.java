package com.smumo.inventorysystem.dto;

import com.smumo.inventorysystem.entities.OrderItems;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private long customerId;
    private String status;
    private List<OrderItems> orderItemsList;
}
