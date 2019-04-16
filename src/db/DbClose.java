package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 关闭操作数据库产生的资源
 *
 * @author celery
 * @version 1.0
 */
public class DbClose {
    public static void closeConnection(PreparedStatement pstmt, Connection conn, ResultSet res) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        try {
            if(res != null) {
                res.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
