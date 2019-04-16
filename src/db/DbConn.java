package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 连接MySql数据库
 *
 * @author celery
 * @version 1.0
 */
public final class DbConn {
    public static Connection getConnection() {
        Connection conn = null;
        String user   = "root";
        String password = "chen9255";
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
        try {
            conn = DriverManager.getConnection(url, user, password);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
