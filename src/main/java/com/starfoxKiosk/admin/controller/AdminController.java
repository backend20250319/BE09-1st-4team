package com.starfoxKiosk.admin.controller;

import com.starfoxKiosk.admin.model.dto.Menu;
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
        Menu menuItemById = adminService.getMenuItemById(id);

        return menuItemById;
    }

    public boolean updateMenuItem(Menu menu) {
        boolean result = adminService.updateMenuItem(menu);

        return result;
    }
}
