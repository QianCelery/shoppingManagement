package tools;

import dao.GoodsDao;
import db.DbClose;
import db.DbConn;
import entity.Goods;
import entity.Gsales;
import entity.SalesMan;

import java.sql.*;
import java.util.ArrayList;

/**
 * 查询&&显示查询结果工具
 *
 * @author celery
 * @version 1.0
 */
public final class QueryPrint {
    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static ResultSet res = null;

    /**
     * 模糊查询工具
     * @param oper 调用者
     * @return
     */
    public static int query(String oper) {
        int gid = -1;
        String shopping = ScannerChoice.scannerInfoString();
        ArrayList<Goods> goodsList = new QueryPrint().queryGoodsKey(gid, shopping);
        if((goodsList.size() <= 0) || goodsList == null) {
            System.err.println("\t！！查无此商品 ！！");
            //調用选择下一步函数
            ScannerChoice.changedInfoNext(oper);
        } else {
            Goods goods = goodsList.get(0);
            System.out.println("\t\t\t\t\t商品列表\n\n");
            System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
            System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+"\t\t"+goods.getGnum());
            if(goods.getGnum() == 0) {
                System.out.println("\t\t该商品已售空");
            } else if (goods.getGnum() <= 10){
                System.out.println("\t\t该商品已不足10件");
            } else {
                System.out.println("\t\t商品库存正常");
            }
            gid = goods.getGid();
        }
        return gid;
    }

    /**
     * 根据商品gid或gname查询商品
     *
     * @param gid   商品gid
     * @param gname 商品名称
     * @return 包含商品信息的列表
     */
    public static ArrayList<Goods> queryGoodsKey(int gid, String gname) {
        ArrayList<Goods> goodsList = new ArrayList<>();
        conn = DbConn.getConnection();
        String sql = "SELECT * FROM goods WHERE gid = ? OR gname = ?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gid);
            pstmt.setString(2, gname);
            res = pstmt.executeQuery();
            addGoodsQueryResult(res, goodsList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.closeConnection(pstmt, conn, res);
        }
        return goodsList;
    }

    /**
     *
     * @return 0:查无此商品， 1：查到商品
     */
    public static int querySettlement(){
        int result = 0;
        ArrayList<Goods> goodsArrayList = GoodsDao.queryGoods(3);   //key:3按关键字查询
        if((goodsArrayList == null) || (goodsArrayList.size() <= 0)) {
            System.err.println("\t！！查无此商品 ！！\n");
        } else {
            System.out.println("\t\t\t\t\t商品列表\n\n");
            System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
            for (int i = 0, length = goodsArrayList.size(); i < length; i++) {
                Goods goods = goodsArrayList.get(i);
                System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+"\t\t"+goods.getGnum());
                if(goods.getGnum() == 0) {
                    System.out.println("\t\t该商品已售空");
                } else if (goods.getGnum() <= 10) {
                    System.out.println("\t\t该商品已不足10件");
                } else {
                    System.out.println("\t\t-");
                }
            }
            result = 1;
        }
        return result;
    }

    /**
     * 按售货员名字精确查找
     * @param sName 售货员名字
     * @return salesManList 售货员列表
     */
    public static ArrayList<SalesMan> querySalesMan (String sName) {
        ArrayList<SalesMan> salesManList = new ArrayList<>();
        conn = DbConn.getConnection();
        String sql = "SELECT * FROM salesman WHERE sname = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sName);
            res = pstmt.executeQuery();
            addSalesManQueryResult(res, salesManList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesManList;
    }

    public static void addGoodsQueryResult (ResultSet res, ArrayList<Goods> goodsList) {
        try {
            while (res.next()) {
                int resultGid = res.getInt("gid");
                String resultGname = res.getString("gname");
                double resultGprice = res.getDouble("gprice");
                int resultGnum = res.getInt("gnum");
                Goods goods = new Goods(resultGid, resultGname, resultGprice, resultGnum);
                goodsList.add(goods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return goodsList;
    }

    public static void addSalesManQueryResult (ResultSet res, ArrayList<SalesMan> salesManList) {
        try {
            while (res.next()) {
                int resultSGid = res.getInt("sid");
                String resultSName = res.getString("sname");
                String  resultSPassword = res.getString("spassword");
                SalesMan salesman = new SalesMan(resultSGid, resultSName, resultSPassword);
                salesManList.add(salesman);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return goodsList;
    }

    public static void addGsalesQueryResult (ResultSet res, ArrayList<Gsales> gsalesArrayList) {
        try {
            while (res.next()) {
                String resultGName = res.getString("gname");
                double resultGPrice = res.getDouble("gprice");
                int resultGNum = res.getInt("gnum");
                int resultAllNum = res.getInt("allSum");
                Gsales gsales = new Gsales(resultGName, resultGPrice, resultGNum, resultAllNum);
                gsalesArrayList.add(gsales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
