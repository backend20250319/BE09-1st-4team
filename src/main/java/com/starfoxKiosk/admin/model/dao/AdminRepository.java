package com.starfoxKiosk.admin.model.dao;

import com.starfoxKiosk.admin.model.dto.Menu;
import com.starfoxKiosk.admin.model.dto.Option;
import com.starfoxKiosk.admin.model.dto.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {

    // 상품 정보 조회
    public List<Menu> getAllMenuItems(Connection conn) {
        List<Menu> menuItems = new ArrayList<>();
        String sql = "SELECT `menuId`, `menuName`, `menuPrice`, `categoryId` FROM tbl_menu";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                menuItems.add(new Menu(rs.getInt("menuId"), rs.getString("menuName"), rs.getInt("menuPrice"), rs.getInt("categoryId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    // 특정 ID의 상품 정보 조회
    public Menu getMenuItemById(Connection conn, int menuId) {
        String sql = "SELECT `menuId`, `menuName`, `menuPrice`, `categoryId` FROM tbl_menu WHERE `menuId` = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, menuId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Menu(rs.getInt("menuId"), rs.getString("menuName"), rs.getInt("menuPrice"), rs.getInt("categoryId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 상품 추가
    public int addMenuItem(Connection conn, Menu menu) {
        int result = 0;
        String sql = "INSERT INTO tbl_menu (`menuName`, `menuPrice`, `categoryId`) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, menu.getName());
            pstmt.setInt(2, menu.getPrice());
            pstmt.setInt(3, menu.getCategoryId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

    // 상품 수정
    public int updateMenuItem(Connection conn, Menu menu) {
        int result = 0;
        String sql = "UPDATE tbl_menu SET `menuName` = ?, `menuPrice` = ?, `categoryId` = ? WHERE `menuId` = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, menu.getName());
            pstmt.setInt(2, menu.getPrice());
            pstmt.setInt(3, menu.getCategoryId());
            pstmt.setInt(4, menu.getId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 상품 삭제
    public int deleteMenuItem(Connection conn, int menuId) {
        int result = 0;
        String sql = "DELETE FROM tbl_menu WHERE `menuId` = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, menuId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 특정 메뉴의 옵션 조회
    public List<Option> getOptionsForMenu(Connection conn, int menuId) {
        List<Option> options = new ArrayList<>();
        String sql = "SELECT o.`optionId`, o.`optionName`, o.`optionDetail`, " +
                "o.`optionType`, o.`maxQuantity`, o.`allowValue`, o.`isRequired` " +
                "FROM `tbl_option` o JOIN `tbl_relation` mor ON o.`optionId` = mor.`optionId` " +
                "WHERE mor.`menuId` = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, menuId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                options.add(new Option(
                        rs.getInt("optionId"),
                        rs.getString("optionName"),
                        rs.getString("optionDetail"),
                        rs.getString("optionType"),
                        rs.getObject("maxQuantity", Integer.class),
                        rs.getString("allowValue")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return options;
    }

    // 옵션 추가
    public int addOption(Connection conn, Option option) {
        int result = 0;
        String sql = "INSERT INTO `tbl_option` (`optionName`, `optionDetail`, `optionType`, `maxQuantity`, `allowValue`, `isRequired`) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, option.getName());
            pstmt.setString(2, option.getDetail());
            pstmt.setString(3, option.getOptionType());
            pstmt.setObject(4, option.getMaxQuantity());
            pstmt.setString(5, option.getAllowedValues());
            pstmt.setBoolean(6, option.isRequired());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 옵션 수정
    public int updateOption(Connection conn, Option option) {
        int result = 0;
        String sql = "UPDATE `tbl_option` SET `optionName` = ?, `optionDetail` = ?, `optionType` = ?, `maxQuantity` = ?, `allowValue` = ?, `isRequired` = ? WHERE `optionId` = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, option.getName());
            pstmt.setString(2, option.getDetail());
            pstmt.setString(3, option.getOptionType());
            pstmt.setObject(4, option.getMaxQuantity());
            pstmt.setString(5, option.getAllowedValues());
            pstmt.setBoolean(6, option.isRequired());
            pstmt.setInt(7, option.getId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 옵션 삭제
    public int deleteOption(Connection conn, int optionId) {
        int result = 0;
        String sql = "DELETE FROM `tbl_option` WHERE `optionId` = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, optionId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 대기 중인 주문 목록 조회
    public List<Order> getWaitingOrders(Connection conn) {
        List<Order> waitingOrders = new ArrayList<>();
        String sql = "SELECT oh.`orderId`, u.`phone` " +
                "FROM `tbl_order_history` oh JOIN `tbl_user` u ON oh.`customId` = u.`customId` " +
                "WHERE oh.`orderId` IN (SELECT `orderId` FROM `tbl_order_item` WHERE `status` = '대기중')";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String fullPhoneNumber = rs.getString("phone");
                String suffix = (fullPhoneNumber != null && fullPhoneNumber.length() >= 4) ? fullPhoneNumber.substring(fullPhoneNumber.length() - 4) : "";
                waitingOrders.add(new Order(rs.getInt("orderId"), suffix, "대기중"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return waitingOrders;
    }
}