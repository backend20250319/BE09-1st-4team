package com.starfoxKiosk.user.pay.service;

import com.starfoxKiosk.common.JDBCTemplate;
import com.starfoxKiosk.user.pay.domain.Order;
import com.starfoxKiosk.user.pay.repository.OrderRepository;

import java.sql.Connection;
import java.util.List;

public class OrderService {

    private final OrderRepository orderRepository = new OrderRepository();

    public void register(Order order) {
        orderRepository.registerOrder(order);
    }

    public void insert(Order order) {
        Connection con = JDBCTemplate.getConnection();
        orderRepository.insertOrder(con, order);
        JDBCTemplate.close(con);
    }

    //public List<Order> getOrders() {
    //    return orderRepository.getOrders();
    //}
}
