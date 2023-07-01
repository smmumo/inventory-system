package com.smumo.inventorysystem.services;

import com.smumo.inventorysystem.dto.CreateOrderDTO;
import com.smumo.inventorysystem.dto.OrderDTO;
import com.smumo.inventorysystem.entities.OrderItems;
import com.smumo.inventorysystem.entities.Orders;
import com.smumo.inventorysystem.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDTO> getByCustomer(long customerId) {
        return orderRepository.findByCustomerId(customerId).stream()
                .map( orders -> new OrderDTO(
                        orders.getId(),
                        orders.getCustomerId(),
                        orders.getStatus(),
                        orders.getOrderItemsList()
                )).collect(Collectors.toList());
    }

    @Override
    public void createOrder(CreateOrderDTO orderDTO) {
        Orders orders = new Orders();
        orders.setStatus("ACTIVE");
        orders.setCustomerId(orderDTO.getCustomerId());
        List<OrderItems> orderItems = new ArrayList<>();
        for (var item : orderDTO.getOrderItemsList()){
            OrderItems orderItems1 =  new OrderItems();
            orderItems1.setPrice(item.getPrice());
            orderItems1.setQuantity(item.getQuantity());
            orderItems1.setProduct(item.getProduct());
            orderItems.add(orderItems1);
        }
        orders.setOrderItemsList(orderItems);
        orderRepository.save(orders);
    }

    @Override
    public void cancelOrders(long orderId) {
        Optional<Orders> orders = orderRepository.findById(orderId);
        if (orders.isEmpty()) {
            throw new RuntimeException("Not Found");
        }else {
            Orders newOrder = new Orders();
            newOrder.setId(orders.get().getId());
            newOrder.setStatus("CANCELLED");
            orderRepository.save(newOrder);
        }
    }

}
