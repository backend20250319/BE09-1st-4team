package com.starfoxKiosk.admin.model.service;

import com.starfoxKiosk.admin.model.dao.AdminRepository;
import com.starfoxKiosk.admin.model.dto.Menu;
import com.starfoxKiosk.admin.model.dto.Option;
import com.starfoxKiosk.admin.model.dto.Order;
import com.starfoxKiosk.common.JDBCTemplate;

import java.sql.Connection;
import java.util.List;

import static com.starfoxKiosk.common.JDBCTemplate.close;

public class AdminService {

    private final AdminRepository adminRepository = new AdminRepository();

    public List<Menu> getAllMenuItems() {
        Connection conn = JDBCTemplate.getConnection();
        List<Menu> menuList = adminRepository.getAllMenuItems(conn);
        close(conn);
        return menuList;
    }

    public Menu getMenuItemById(int menuId) {
        Connection conn = JDBCTemplate.getConnection();
        Menu menu = adminRepository.getMenuItemById(conn, menuId);
        close(conn);
        return menu;
    }

    public boolean addMenuItem(Menu menuItem) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminRepository.addMenuItem(conn, menuItem);
        boolean isSuccess = processResult(conn, result);
        close(conn);
        return isSuccess;
    }

    public boolean updateMenuItem(Menu menu) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminRepository.updateMenuItem(conn, menu);
        boolean isSuccess = processResult(conn, result);
        close(conn);
        return isSuccess;
    }

    public boolean deleteMenuItem(int menuId) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminRepository.deleteMenuItem(conn, menuId);
        boolean isSuccess = processResult(conn, result);
        close(conn);
        return isSuccess;
    }

    public List<Option> getOptionsForMenu(int menuId) {
        Connection conn = JDBCTemplate.getConnection();
        List<Option> options = adminRepository.getOptionsForMenu(conn, menuId);
        close(conn);
        return options;
    }

    public boolean addOption(Option option) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminRepository.addOption(conn, option);
        boolean isSuccess = processResult(conn, result);
        close(conn);
        return isSuccess;
    }

    public boolean updateOption(Option option) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminRepository.updateOption(conn, option);
        boolean isSuccess = processResult(conn, result);
        close(conn);
        return isSuccess;
    }

    public boolean deleteOption(int optionId) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminRepository.deleteOption(conn, optionId);
        boolean isSuccess = processResult(conn, result);
        close(conn);
        return isSuccess;
    }

    public List<Order> getWaitingOrders() {
        Connection conn = JDBCTemplate.getConnection();
        List<Order> waitingOrders = adminRepository.getWaitingOrders(conn);
        close(conn);
        return waitingOrders;
    }

    private boolean processResult(Connection conn, int result) {
        boolean isSuccess = false;
        if (result > 0) {
            JDBCTemplate.commit(conn);
            isSuccess = true;
        } else {
            JDBCTemplate.rollback(conn);
        }
        close(conn);
        return isSuccess;
    }
}