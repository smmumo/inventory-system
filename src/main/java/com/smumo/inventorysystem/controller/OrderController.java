package com.smumo.inventorysystem.controller;

import com.smumo.inventorysystem.dto.CreateOrderDTO;
import com.smumo.inventorysystem.dto.OrderDTO;
import com.smumo.inventorysystem.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/list")
    public ResponseEntity<String> createOrder(CreateOrderDTO order){
        orderService.createOrder(order);
       // URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            //    .buildAndExpand(savedOrder.getId()).toUri();
        return ResponseEntity.ok("success");
    }

    @GetMapping("/{customerId}")
    public List<OrderDTO> getOrders(@PathVariable long customerId){
        return  orderService.getByCustomer(customerId);
    }

    @PutMapping("/cancel/{orderId}")
    public void cancelOrder(@PathVariable long orderId){
        orderService.cancelOrders(orderId);
    }

}
