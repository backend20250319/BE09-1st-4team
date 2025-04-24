package com.starfoxKiosk.user.pay.view;

import com.starfoxKiosk.user.pay.domain.Order;

import java.util.List;

public class OrderView {
    public void displayOrders(List<Order> orders) {
        System.out.println("=== 주문 목록 ===");
        for (Order order : orders) {
            System.out.println("주문 ID: " + order.getOrderId() + ", 고객 ID: " + order.getUserId());
        }
    }


}
