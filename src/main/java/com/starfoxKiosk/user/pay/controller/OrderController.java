package com.starfoxKiosk.user.pay.controller;

import com.starfoxKiosk.user.pay.domain.Order;
import com.starfoxKiosk.user.pay.service.OrderService;
import com.starfoxKiosk.user.pay.view.OrderView;

import java.util.List;
import java.util.Scanner;

public class OrderController {

    private final OrderService orderService;
    private final OrderView orderView;

    public OrderController(OrderService orderService, OrderView orderView) {
        this.orderService = orderService;
        this.orderView = orderView;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== 주문 관리 =====");
            System.out.println("1. 주문 등록");
            System.out.println("2. 주문 목록 조회");
            System.out.println("0. 종료");
            System.out.print("선택: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("주문 관리를 종료합니다.");
                    return;
                case 1:
                    inputOrder();
                    break;
                case 2:
                    List<Order> orders = getAllOrders();
                    orderView.displayOrders(orders);
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    private void inputOrder() {
        Scanner sc = new Scanner(System.in);
        System.out.print("주문 ID를 입력하세요: ");
        int orderId = sc.nextInt();
        System.out.print("고객 ID를 입력하세요: ");
        int userId = sc.nextInt();

        createOrder(orderId, userId);
        System.out.println("주문이 등록되었습니다.");
    }

    public void createOrder(int orderId, int userId) {
        Order order = new Order(orderId, userId);
        orderService.register(order);
    }

    public List<Order> getAllOrders() {
        return orderService.getOrders();
    }
}
