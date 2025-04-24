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
        String sql = "SELECT `메뉴 ID`, `메뉴명`, `메뉴가격`, `카테고리 ID` FROM menu";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                menuItems.add(new Menu(rs.getInt("메뉴 ID"), rs.getString("메뉴명"), rs.getInt("메뉴가격"), rs.getInt("카테고리 ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    // 특정 ID의 상품 정보 조회
    public Menu getMenuItemById(Connection conn, int menuId) {
        String sql = "SELECT `메뉴 ID`, `메뉴명`, `메뉴가격`, `카테고리 ID` FROM menu WHERE `메뉴 ID` = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, menuId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Menu(rs.getInt("메뉴 ID"), rs.getString("메뉴명"), rs.getInt("메뉴가격"), rs.getInt("카테고리 ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 상품 추가
    public int addMenuItem(Connection conn, String name, int price, int categoryId) {
        int result = 0;
        String sql = "INSERT INTO menu (`메뉴명`, `메뉴가격`, `카테고리 ID`) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, price);
            pstmt.setInt(3, categoryId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 상품 수정
    public int updateMenuItem(Connection conn, Menu menu) {
        int result = 0;
        String sql = "UPDATE menu SET `메뉴명` = ?, `메뉴가격` = ?, `카테고리 ID` = ? WHERE `메뉴 ID` = ?";
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
    public int deleteMenuItem(Connection conn, int id) {
        int result = 0;
        String sql = "DELETE FROM menu WHERE `메뉴 ID` = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 특정 메뉴의 옵션 조회
    public List<Option> getOptionsForMenu(Connection conn, int menuId) {
        List<Option> options = new ArrayList<>();
        String sql = "SELECT o.`옵션 ID`, o.`옵션 이름`, o.`옵션 상세`, o.`필수 여부`, " +
                "o.`옵션 타입`, o.`최대 수량`, o.`허용 값` " +
                "FROM `option` o JOIN `menu_option_relation` mor ON o.`옵션 ID` = mor.`옵션 ID` " +
                "WHERE mor.`메뉴 ID` = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, menuId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                options.add(new Option(
                        rs.getInt("옵션 ID"),
                        rs.getString("옵션 이름"),
                        rs.getString("옵션 상세"),
                        rs.getString("옵션 타입"),
                        rs.getInt("최대 수량"),
                        rs.getString("허용 값"),
                        rs.getBoolean("필수 여부")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return options;
    }

    // 옵션 추가
    public int addOption(Connection conn, String name, String detail, String optionType, Integer maxQuantity, String allowedValues, boolean isRequired) {
        int result = 0;
        String sql = "INSERT INTO `option` (`옵션 이름`, `옵션 상세`, `옵션 타입`, `최대 수량`, `허용 값`, `필수 여부`) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, detail);
            pstmt.setString(3, optionType);
            pstmt.setObject(4, maxQuantity); // Integer 타입은 setObject 사용
            pstmt.setString(5, allowedValues);
            pstmt.setBoolean(6, isRequired);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 옵션 수정
    public int updateOption(Connection conn, int id, String name, String detail, String optionType, Integer maxQuantity, String allowedValues, boolean isRequired) {
        int result = 0;

        String sql = "UPDATE `option` SET ";
        //
        if(name != null) {
            sql += "`옵션 이름` = ? ";
        } else if(detail != null) {
            if (name != null) {
                sql += ", ";
            }
            sql += "`옵션 상세` = ? ";
        }
        sql += "WHERE `옵션 ID` = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, detail);
            pstmt.setInt(3, id);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 옵션 삭제
    public int deleteOption(Connection conn, int id) {
        int result = 0;
        String sql = "DELETE FROM `option` WHERE `옵션 ID` = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 메뉴에 옵션 연결
    public int addOptionToMenu(Connection conn, int menuId, int optionId) {
        int result = 0;
        String sql = "INSERT INTO `menu_option_relation` (`메뉴 ID`, `옵션 ID`) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, menuId);
            pstmt.setInt(2, optionId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 메뉴에서 옵션 해제
    public int removeOptionFromMenu(Connection conn, int menuId, int optionId) {
        int result = 0;
        String sql = "DELETE FROM `menu_option_relation` WHERE `메뉴 ID` = ? AND `옵션 ID` = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, menuId);
            pstmt.setInt(2, optionId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 대기 중인 주문 목록 조회
    public List<Order> getWaitingOrders(Connection conn) {
        List<Order> waitingOrders = new ArrayList<>();
        String sql = "SELECT oh.orderId, u.phone \n" +
                "                FROM `tbl_order_history` oh JOIN `tbl_user` u ON oh.customId = u.customId\n" +
                "                WHERE oh.status = 'START'"; // '옵션 값' -> '상태'로 변경 가정
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                waitingOrders.add(new Order(rs.getInt("orderId"), rs.getString("phone").substring(rs.getString("phone").length() - 4), "대기중"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return waitingOrders;
    }

    // 주문 상태를 '제조 완료'로 변경
    public int markOrderAs제조완료(Connection conn, int orderId) {
        int result = 0;
        String sql = "UPDATE `order_item` SET `상태` = '제조 완료' WHERE `주문 ID` = ?"; // '옵션 값' -> '상태'로 변경 가정
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 제조 완료된 주문 목록 조회
    public List<Order> get제조완료Orders(Connection conn) {
        List<Order> 제조완료Orders = new ArrayList<>();
        String sql = "SELECT oh.`주문 ID`, u.`전화번호` " +
                "FROM `order_history` oh JOIN `user` u ON oh.`고객 ID` = u.`고객 ID` " +
                "WHERE oh.`주문 ID` IN (SELECT `주문 ID` FROM `order_item` WHERE `상태` = '제조 완료')"; // '옵션 값' -> '상태'로 변경 가정
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                제조완료Orders.add(new Order(rs.getInt("주문 ID"), rs.getString("전화번호").substring(rs.getString("전화번호").length() - 4), "제조 완료"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 제조완료Orders;
    }

    // 주문 상태를 '고객 픽업 완료'로 변경
    public int markOrderAs픽업완료(Connection conn, int orderId) {
        int result = 0;
        String sql = "UPDATE `order_item` SET `상태` = '고객 픽업 완료' WHERE `주문 ID` = ?"; // '옵션 값' -> '상태'로 변경 가정
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 주문 히스토리 저장
    public int saveOrderToHistory(Connection conn, int orderId, int customerId) {
        int result = 0;
        String sql = "INSERT INTO `order_history` (`주문 ID`, `고객 ID`) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, customerId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 모든 옵션 조회 (공통 옵션 관리 기능 구현을 위해 추가)
    public List<Option> getAllOptions(Connection conn) {
        List<Option> options = new ArrayList<>();
        String sql = "SELECT `옵션 ID`, `옵션 이름`, `옵션 상세`, `옵션 타입`, `최대 수량`, `허용 값`, `필수 여부` FROM `option`";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                options.add(new Option(
                        rs.getInt("옵션 ID"),
                        rs.getString("옵션 이름"),
                        rs.getString("옵션 상세"),
                        rs.getString("옵션 타입"),
                        rs.getInt("최대 수량"),
                        rs.getString("허용 값"),
                        rs.getBoolean("필수 여부")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return options;
    }

    // 특정 ID의 옵션 조회 (공통 옵션 관리 기능 구현을 위해 추가)
    public Option getOptionById(Connection conn, int optionId) {
        String sql = "SELECT `옵션 ID`, `옵션 이름`, `옵션 상세`, `옵션 타입`, `최대 수량`, `허용 값`, `필수 여부` FROM `option` WHERE `옵션 ID` = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, optionId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Option(
                        rs.getInt("옵션 ID"),
                        rs.getString("옵션 이름"),
                        rs.getString("옵션 상세"),
                        rs.getString("옵션 타입"),
                        rs.getInt("최대 수량"),
                        rs.getString("허용 값"),
                        rs.getBoolean("필수 여부")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}