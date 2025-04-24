package com.starfoxKiosk.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {


    public static Connection getConnection() {
        Properties props = new Properties();
        Connection con = null;
        try {
            props.load(
                new FileReader(
                    "src/main/java/com/starfoxKiosk/config/jdbc-info.properties"));

            String url = props.getProperty("url");

            con = DriverManager.getConnection(url, props);

            // 자동 커밋 설정을 수동 커밋 설정으로 변경하여 서비스 계층에서 트랜잭션을 컨트롤할 수 있도록 함
            con.setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return con;
    }

    /* connection을 닫는 개념은 별도의 메소드로 분리하고 실제 닫는 시점은 Service 계층에서 진행 */
    public static void close(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Statement con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void commit(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rollback(Connection con) {
        try {
            if (con != null && con.isClosed()) {
                con.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
