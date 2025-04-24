package com.starfoxKiosk.user.pay.service;

import com.starfoxKiosk.user.pay.domain.Order;
import com.starfoxKiosk.user.pay.repository.OrderRepository;

import java.util.List;

public class OrderService {

    private final OrderRepository orderRepository = new OrderRepository();

    public void register(Order order) {
        orderRepository.registerOrder(order);
    }


    public List<Order> getOrders() {
        return orderRepository.getOrders();
    }
}
