package dao;

import db.DbClose;
import db.DbConn;
import entity.Goods;
import tools.QueryPrint;
import tools.ScannerChoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 数据库Goods表操作
 *
 * @author celery
 * @version 1.0
 */
public final class GoodsDao {
    private static PreparedStatement pstmt;
    private static Connection conn = null;
    private static ResultSet res = null;

    /**
     * 添加商品到Goods表
     *
     * @param goods 添加的商品
     * @return boolean
     */
    public static boolean addGoods(Goods goods) {
        boolean bool = false;
        conn = DbConn.getConnection();
        String sql = "INSERT INTO goods(gname, gprice, gnum) VALUES(?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goods.getGname());
            pstmt.setDouble(2, goods.getGprice());
            pstmt.setInt(3, goods.getGnum());
            int result = pstmt.executeUpdate();
            if (result > 0) {
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
     * 更新goods表中商品数据
     *
     * @param key   选择更改商品的信息
     * @param goods 商品对象
     * @return bool
     */
    public static boolean updateGoods(int key, Goods goods) {
        boolean bool = false;
        conn = DbConn.getConnection();
        switch (key) {
            case 1: //key:1 更改商品名称
                String sqlName = "UPDATE goods SET gname = ? WHERE gid = ?";
                try {
                    pstmt = conn.prepareStatement(sqlName);
                    pstmt.setString(1, goods.getGname());
                    pstmt.setInt(2, goods.getGid());
                    int result = pstmt.executeUpdate();
                    if (result > 0) {
                        bool = true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.closeConnection(pstmt, conn, res);
                }
                break;
            case 2: //key:2更改商品价格
                String sqlPrice = "UPDATE goods SET gprice = ? WHERE gid = ?";
                try {
                    pstmt = conn.prepareStatement(sqlPrice);
                    pstmt.setDouble(1, goods.getGprice());
                    pstmt.setInt(2, goods.getGid());
                    int result = pstmt.executeUpdate();
                    if (result > 0) {
                        bool = true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.closeConnection(pstmt, conn, res);
                }
            case 3: //key:3 更改商品数量
                String sqlNum = "UPDATE goods SET gnum = ? WHERE gid = ?";
                try {
                    pstmt = conn.prepareStatement(sqlNum);
                    pstmt.setInt(1, goods.getGnum());
                    pstmt.setInt(2, goods.getGid());
                    int result = pstmt.executeUpdate();
                    if (result > 0) {
                        bool = true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.closeConnection(pstmt, conn, res);
                }
            default:
                break;
        }
        return bool;
    }

    /**
     * 从数据库中删除商品
     * @param gid 商品编号
     * @return boolean
     */
    public static boolean deleteGoods (int gid) {
        boolean bool = false;
        conn = DbConn.getConnection();
        String sql = "DELETE FROM goods WHERE gid = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gid);
            int result = pstmt.executeUpdate();
            if(result > 0) {
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
     * 查询商品
     * @param key 查询方式
     * @return 商品列表
     */
    public static ArrayList<Goods> queryGoods (int key) {
        ArrayList<Goods> goodslist = new ArrayList<>();
        conn = DbConn.getConnection();
        switch (key) {
            case 1: //按照商品数量升序进行查询
                String sqlNum = "SELECT * FROM goods ORDER BY gnum";
                try {
                    pstmt = conn.prepareStatement(sqlNum);
                    res = pstmt.executeQuery();
                    QueryPrint.addGoodsQueryResult(res, goodslist);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.closeConnection(pstmt, conn, res);
                }
                break;
            case 2: //按商品价格升序进行排序
                String sqlPrice = "SELECT * FROM goods ORDER BY gprice";
                try {
                    pstmt = conn.prepareStatement(sqlPrice);
                    res = pstmt.executeQuery();
                    QueryPrint.addGoodsQueryResult(res, goodslist);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.closeConnection(pstmt, conn, res);
                }
                break;
            case 3: //按关键字查询
                String sqlKeyWords = "SELECT * FROM GOODS WHERE GNAME LIKE ?";//"SELECT * FROM goods WHERE gname LIKE %?";
                String keyWords = ScannerChoice.scannerInfoString();
                keyWords = "%" + keyWords + "%";
                try {
                    pstmt = conn.prepareStatement(sqlKeyWords);
                    pstmt.setString(1, keyWords);
                    res = pstmt.executeQuery();
                    QueryPrint.addGoodsQueryResult(res, goodslist);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DbClose.closeConnection(pstmt, conn, res);
                }
        }
        return goodslist;
    }

    /**
     * 查询展示所有商品
     * @return goodslist商品列表
     */
    public static ArrayList<Goods> displayGoods() {
        ArrayList<Goods> goodslist = new ArrayList<>();
        conn = DbConn.getConnection();
        String sql = "SELECT * FROM goods";
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            QueryPrint.addGoodsQueryResult(res, goodslist);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.closeConnection(pstmt, conn, res);
        }
        return goodslist;
    }
}
