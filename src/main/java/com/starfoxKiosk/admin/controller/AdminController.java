package com.starfoxKiosk.admin.controller;

import com.starfoxKiosk.admin.model.dto.Menu;

import com.starfoxKiosk.admin.model.dto.Option;
import com.starfoxKiosk.admin.model.dto.Order;
import com.starfoxKiosk.admin.model.service.AdminService;

import java.util.List;

public class AdminController {

    private final AdminService adminService = new AdminService();

    public void viewMenuItems() {
        List<Menu> menuList = adminService.getAllMenuItems();
        if (menuList.isEmpty()) {
            System.out.println("등록된 상품이 없습니다.");
        } else {
            System.out.println("\n--- 상품 목록 ---");
            for (Menu menu : menuList) {
                System.out.println(menu);
            }
            System.out.println("------------------");
        }
    }

    public Menu getMenuItemById(int id) {
        return adminService.getMenuItemById(id);
    }

    public boolean updateMenuItem(Menu menu) {
        return adminService.updateMenuItem(menu);
    }

    public boolean addMenuItem(Menu menu) {
        return adminService.addMenuItem(menu);
    }

    public boolean deleteMenuItem(Menu menu) {
        return adminService.deleteMenuItem(menu.getId());
    }

    public List<Option> getOptionsForMenu(Menu menu) {
        return adminService.getOptionsForMenu(menu.getId());
    }

    public boolean addOption(Option option) {
        return adminService.addOption(option);
    }

    public boolean updateOption(Option option) {
        return adminService.updateOption(option);
    }

    public boolean deleteOption(Option option) {
        return adminService.deleteOption(option.getId());
    }

    public List<Order> getWaitingOrders(Order order) {
        return adminService.getWaitingOrders();
    }

    // 대기중

    public List<Order> viewWaitingOrders() {

        List<Order> orderList = adminService.getWaitingOrders();

        return orderList;
    }

    // 제조완료

    public List<Order> markOrderAs제조완료() {

       List<Order> orderList = adminService.get제조완료Orders();
        return orderList;
    }

   // 픽업완료

   public List<Order> markOrderAs픽업완료() {

       System.out.println("제품 픽업 완료 하였습니다.");
       return List.of();
   }
}
