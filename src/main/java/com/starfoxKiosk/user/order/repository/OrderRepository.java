package com.starfoxKiosk.user.order.repository;

import com.starfoxKiosk.user.order.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private List<Order> orders = new ArrayList<>();

    public OrderRepository() {
    }

    public void registerOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return OrderRepository.this.orders;
    }

}
