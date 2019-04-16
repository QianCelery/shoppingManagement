package dao;

import db.DbClose;
import db.DbConn;
import entity.Gsales;
import tools.QueryPrint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 数据库sales表操作
 *
 * @author celery
 * @version 1.0
 */
public final class GsalesDao {
    private static PreparedStatement pstmt;
    private static Connection conn = null;
    private static ResultSet res = null;

    /**
     * 购物结算 向gsales表中插入商品数据
     * @param gsales 售卖商品信息
     * @return boolean
     */
    public static boolean settlementGsales(Gsales gsales) {
        boolean bool = false;
        conn = DbConn.getConnection();
        String sql = "INSERT INTO gsales(gid, sid, snum) VALUES(?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gsales.getGid());
            pstmt.setInt(2, gsales.getSid());
            pstmt.setInt(3, gsales.getsNum());
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
     * 展示当天售出的商品信息
     * @return gsalesArrayList 售出的商品列表
     */
    public static ArrayList<Gsales> dailyGsales() {
        ArrayList<Gsales> gsalesArrayList = new ArrayList<>();
        conn = DbConn.getConnection();
        String sql = "select gname,gprice,gnum, allSum from goods, (select gid as salesid,sum(snum) as allSum from gsales where TO_DAYS(sdate) = TO_DAYS(now()) group by gid)as query where gid = salesid";
        try {
            pstmt = conn.prepareStatement(sql);
            res = pstmt.executeQuery();
            QueryPrint.addGsalesQueryResult(res, gsalesArrayList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gsalesArrayList;
    }

}
