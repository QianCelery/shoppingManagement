package dao;

import db.DbClose;
import db.DbConn;
import entity.SalesMan;
import tools.QueryPrint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class SalesManDao {
    private static PreparedStatement pstmt;
    private static Connection conn = null;
    private static ResultSet res = null;

    public static ArrayList<SalesMan> checkLogin(String sName) {
        ArrayList<SalesMan> salesManList = new ArrayList<>();
        conn = DbConn.getConnection();
        String sql = "SELECT * FROM salesman WHERE sname = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sName);
            res = pstmt.executeQuery();
            QueryPrint.addSalesManQueryResult(res, salesManList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.closeConnection(pstmt, conn, res);
        }
        return salesManList;
    }

    /**
     * 添加售货员到salesman表
     *
     * @param salesMan 添加的售货员
     * @return boolean
     */
    public static boolean addSalesMan(SalesMan salesMan) {
        boolean bool = false;
        conn = DbConn.getConnection();
        String sql = "INSERT INTO salesman(sname, spassword) VALUES(?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, salesMan.getsName());
            pstmt.setString(2, salesMan.getsPassword());
            if (pstmt.executeUpdate() > 0) {
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.closeConnection(pstmt, conn, res);
        }
        return bool;
    }

    /**
     * @param key      选择更改售货员的信息 1：名字 2：密码
     * @param salesMan 售货员对象
     * @return boolean
     */
    public static boolean updateSalesMan(int key, SalesMan salesMan) {
        boolean bool = false;
        conn = DbConn.getConnection();
        switch (key) {
            case 1: //更改售货员名称
                String sqlName = "UPDATE salesman SET sname = ? WHERE sid = ?";
                try {
                    pstmt = conn.prepareStatement(sqlName);
                    pstmt.setString(1, salesMan.getsName());
                    pstmt.setInt(2, salesMan.getSid());
                    if (pstmt.executeUpdate() > 0) {
                        bool = true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.closeConnection(pstmt, conn, res);
                }
                break;
            case 2: //更改售货员密码
                String sqlPassword = "UPDATE salesman SET password = ? WHERE sid = ?";
                try {
                    pstmt = conn.prepareStatement(sqlPassword);
                    pstmt.setString(1, salesMan.getsPassword());
                    pstmt.setInt(2, salesMan.getSid());
                    if (pstmt.executeUpdate() > 0) {
                        bool = true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.closeConnection(pstmt, conn, res);
                }
        }
        return bool;
    }

    /**
     * 从salesman表删除售货员
     *
     * @param sid 售货员编号
     * @return boolean
     */
    public static boolean deleteSalesMan(int sid) {
        boolean bool = false;
        conn = DbConn.getConnection();
        String sql = "DELETE FROM salesman WHERE sid = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sid);
            if(pstmt.executeUpdate() > 0) {
                bool = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.closeConnection(pstmt, conn, res);
        }
        return bool;
    }

    /**
     * 模糊查询售货员
     * @param sNmae 用户名关键字
     * @return salesManList 售货员列表
     */
    public static ArrayList<SalesMan> querySaleMan(String sNmae) {
        ArrayList<SalesMan>  salesManList = new ArrayList<>();
        conn = DbConn.getConnection();
        String sql = "SELECT * FROM salesMan LIKE ?";
        sNmae = "%" + sNmae + "%";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sNmae);
            res = pstmt.executeQuery();
            QueryPrint.addSalesManQueryResult(res, salesManList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.closeConnection(pstmt, conn, res);
        }
        return salesManList;
    }

    /**
     * 显示所有售货员
     * @return salesManList 售货员列表
     */
    public static ArrayList<SalesMan> displaySalesMan() {
        ArrayList<SalesMan>  salesManList = new ArrayList<>();
        conn = DbConn.getConnection();
        String sql = "SELECT * FROM salesMan";
        try{
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            QueryPrint.addSalesManQueryResult(res, salesManList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesManList;
    }
}
