package com.starfoxKiosk.user;

import com.starfoxKiosk.user.pay.controller.OrderController;
import com.starfoxKiosk.user.pay.service.OrderService;
import com.starfoxKiosk.user.pay.view.OrderView;


public class UserApplication {

    public static void main(String[] args) {

        OrderController orController = new OrderController(new OrderService(),new OrderView());
        orController.start();




    }
}
