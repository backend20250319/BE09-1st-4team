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

    private final AdminRepository adminCRUD = new AdminRepository();

    public List<Menu> getAllMenuItems() {
        Connection conn = JDBCTemplate.getConnection();
        List<Menu> menuList = adminCRUD.getAllMenuItems(conn);
        close(conn);
        return menuList;
    }

    public Menu getMenuItemById(int menuId) {
        Connection conn = JDBCTemplate.getConnection();
        Menu menu = adminCRUD.getMenuItemById(conn, menuId);
        close(conn);
        return menu;
    }

    public boolean addMenuItem(String name, int price, int categoryId) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminCRUD.addMenuItem(conn, name, price, categoryId);
        return processResult(conn, result);
    }

    public boolean updateMenuItem(Menu menu) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminCRUD.updateMenuItem(conn, menu);

        close(conn);
        return processResult(conn, result);
    }

    public boolean deleteMenuItem(int id) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminCRUD.deleteMenuItem(conn, id);
        return processResult(conn, result);
    }

    public List<Option> getOptionsForMenu(int menuId) {
        Connection conn = JDBCTemplate.getConnection();
        List<Option> options = adminCRUD.getOptionsForMenu(conn, menuId);
        close(conn);
        return options;
    }

    public boolean addOption(String name, String detail, String optionType, Integer maxQuantity, String allowedValues, boolean isRequired) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminCRUD.addOption(conn, name, detail, optionType, maxQuantity, allowedValues, isRequired);
        return processResult(conn, result);
    }

    public boolean updateOption(int id, String name, String detail, String optionType, Integer maxQuantity, String allowedValues, boolean isRequired) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminCRUD.updateOption(conn, id, name, detail, optionType, maxQuantity, allowedValues, isRequired);
        return processResult(conn, result);
    }

    public boolean deleteOption(int id) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminCRUD.deleteOption(conn, id);
        return processResult(conn, result);
    }

    public boolean addOptionToMenu(int menuId, int optionId) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminCRUD.addOptionToMenu(conn, menuId, optionId);
        return processResult(conn, result);
    }

    public boolean removeOptionFromMenu(int menuId, int optionId) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminCRUD.removeOptionFromMenu(conn, menuId, optionId);
        return processResult(conn, result);
    }

    public List<Order> getWaitingOrders() {
        Connection conn = JDBCTemplate.getConnection();
        List<Order> waitingOrders = adminCRUD.getWaitingOrders(conn);
        close(conn);
        return waitingOrders;
    }

    public boolean markOrderAs제조완료(int orderId) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminCRUD.markOrderAs제조완료(conn, orderId);
        return processResult(conn, result);
    }

    public List<Order> get제조완료Orders() {
        Connection conn = JDBCTemplate.getConnection();
        List<Order> 제조완료Orders = adminCRUD.get제조완료Orders(conn);
        close(conn);
        return 제조완료Orders;
    }

    public boolean markOrderAs픽업완료(int orderId) {
        Connection conn = JDBCTemplate.getConnection();
        int result = adminCRUD.markOrderAs픽업완료(conn, orderId);
        return processResult(conn, result);
    }

    public List<Option> getAllOptions() {
        Connection conn = JDBCTemplate.getConnection();
        List<Option> allOptions = adminCRUD.getAllOptions(conn);
        close(conn);
        return allOptions;
    }

    public Option getOptionById(int optionId) {
        Connection conn = JDBCTemplate.getConnection();
        Option option = adminCRUD.getOptionById(conn, optionId);
        close(conn);
        return option;
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