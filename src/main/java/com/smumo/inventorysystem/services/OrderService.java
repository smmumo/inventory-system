package com.smumo.inventorysystem.services;

import com.smumo.inventorysystem.dto.CreateOrderDTO;
import com.smumo.inventorysystem.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<OrderDTO> getByCustomer(long customerId);
    void createOrder(CreateOrderDTO orderDTO);
    //String placeOrder(OrderRequestDTO orderRequestDTO);
    void cancelOrders(long orderId);
}
