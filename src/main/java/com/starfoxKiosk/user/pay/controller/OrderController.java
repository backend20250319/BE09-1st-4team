package com.starfoxKiosk.user.pay.controller;

import com.starfoxKiosk.user.membership.controller.MembershipController;
import com.starfoxKiosk.user.membership.domain.Membership;
import com.starfoxKiosk.user.membership.view.MemshipView;
import com.starfoxKiosk.user.menu.domain.MenuWithOptions;
import com.starfoxKiosk.user.pay.domain.Order;
import com.starfoxKiosk.user.pay.service.OrderService;
import com.starfoxKiosk.user.pay.view.OrderView;
import com.starfoxKiosk.user.pay.view.PaymentView;

import java.util.List;
import java.util.Scanner;

public class OrderController {

    private final OrderService orderService;
    private final OrderView orderView;
    private final MemshipView memshipView;
    private final PaymentView paymentView;

    public OrderController() {
        this.orderService = new OrderService();
        this.orderView = new OrderView();
        this.memshipView = new MemshipView();
        this.paymentView =  new PaymentView();
    }

    public void start(List<MenuWithOptions> cart) {

        MembershipController membershipController = new MembershipController();
        Membership membership = new membershipController.start();

        int total =  cart.stream().mapToInt(MenuWithOptions::get).sum();
        if(membership != null) {
            double discountRate = membership.getMembershipName().equals("Gold") ? 0.1
                    : membership.getMembershipName().equals("Silver") ? 0.05 : 0.01;

            int discount = (int)(discountRate * total);
            total -= discount;
            System.out.println("할인 적용됨: -" + discount + "원 (" + (int)(discountRate * 100) + "% 할인)");
        }

        System.out.println("총 결제 금액: " + total + "원");
        String payType = paymentView.selectPaymentMethod();
        paymentView.showResult(true);


        // 주문 생성
        int newOrderId = generateOrderId();
        int userId = (membership != null) ? membership.getPhoneNumber().hashCode() : 0;


        System.out.println("주문 완료! 대기번호: " + newOrderId + " [상태: " + order.getStatus() + "]");
    }


    private int generateOrderId() {
        return (int) (System.currentTimeMillis() % 100000); // 단순 ID 생성
    }
}
