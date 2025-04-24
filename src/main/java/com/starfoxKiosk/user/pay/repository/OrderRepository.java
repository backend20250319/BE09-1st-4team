package com.starfoxKiosk.user.pay.repository;

import com.starfoxKiosk.user.pay.domain.Order;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.starfoxKiosk.common.JDBCTemplate.close;

public class OrderRepository {

    private final Properties prop;

    public OrderRepository() {
        prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/starfoxKiosk/user/pay/mapper/OrderMapper.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerOrder(Order order) {
        //orders.add(order);

    }


    public int insertOrder(Connection con, Order order) {
        System.out.println(" 주문 ===> " + order);
        PreparedStatement pstmt = null;
        int result = 0;

        String sql = prop.getProperty("insertOrder");
        System.out.println("sql = " + sql);

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(2, order.getUserId());
            pstmt.setInt(1, order.getOrderId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        System.out.println("result ===> " + result);
        return result;

    }

}
