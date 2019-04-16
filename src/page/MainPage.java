package page;

import dao.GoodsDao;
import dao.GsalesDao;
import dao.SalesManDao;
import entity.Goods;
import entity.Gsales;
import entity.SalesMan;
import tools.QueryPrint;
import tools.ScannerChoice;

import java.util.ArrayList;

/**
 * 管理系统主界面
 *
 * @author celery
 * @version 1.0
 */
public final class MainPage extends ScannerChoice {
    public static void main(String[] arg) {
        mainPage();
    }

    /**
     * 主界面
     *
     * @author celeru
     * @version 1.0
     */
    public static void mainPage() {
        System.out.println("***************************\n");
        System.out.println("\t 1.商品维护\n");
        System.out.println("\t 2.前台收银\n");
        System.out.println("\t 3.商品管理\n");
        System.out.println("***************************");

        System.out.println("\n请输入选项,或者按0退出.");
        while (true) {
            String choice = scannerInfoString();
            String regex = "[0-3]";  //正则表达式
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        System.out.println("------------------");
                        System.out.println("您已经退出系统!");
                        System.exit(1);//退出程序，返回值随便设置
                        break;
                    case 1:
                        maintenance();
                        break;
                    case 2:
                        cashierPage();
                        break;
                    case 3:
                        commdityManagementPage();
                        ;
                        break;
                    default:
                        break;
                }
            }

        }
    }


    /**
     * 商品维护界面
     *
     * @author celery
     * @version 1.0
     */
    public static void maintenance() {
        System.out.println("***************************\n");
        System.out.println("\t 1.添加商品\n");
        System.out.println("\t 2.更改商品\n");
        System.out.println("\t 3.删除商品\n");
        System.out.println("\t 4.查询商品\n");
        System.out.println("\t 5.显示所有商品\n");
        System.out.println("***************************");

        System.out.println("\n请输入选项,或者按 0 返回上一级菜单.");
        while (true) {
            String choice = scannerInfoString();
            String regex = "[0-5]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        mainPage();
                        break;
                    case 1:
                        GoodsPage.addGoodsPage();   //添加商品
                        break;
                    case 2:
                        GoodsPage.updateGoodsPage();    //更改商品
                        break;
                    case 3:
                        GoodsPage.deleteGoodsPage();    //删除商品
                        break;
                    case 4:
                        GoodsPage.queryGoodsPage();     //查询商品
                        break;
                    case 5:
                        GoodsPage.displayGoodsPage();    //显示所有商品
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * 前台收银界mina
     *
     * @author celery
     * @version 1.0
     */
    public static void cashierPage() {
        System.out.println("\n*******欢迎使用商超购物管理系统*******\n");
        System.out.println("\t 1.登录系统\n");
        System.out.println("\t 2.退出\n");
        System.out.println("-----------------------------");
        System.out.println("请输入选项,或者按 0 返回上一级菜单.");
        while (true) {
            String choice = scannerInfoString();
            String regex = "[0-2]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        mainPage();
                        break;
                    case 1:
                        int loginNum = 3;   //三次登录机会
                        while (loginNum != 0) {
                            loginNum--;
                            System.out.println("---用户名---");
                            String sName = scannerInfoString();
                            ArrayList<SalesMan> salesManList = SalesManDao.checkLogin(sName);
                            if ((salesManList == null) || (salesManList.size() <= 0)) {
                                System.err.println("\t!!用户名输入有误!!\n");
                                System.out.println("\n剩余登陆次数：" + loginNum);
                            } else {
                                System.out.println("---密码---");
                                String sPssWord = scannerInfoString();
                                SalesMan salesMan = salesManList.get(0);
                                if (sPssWord.equals(salesMan.getsPassword())) {
                                    System.out.println("\t  ---账户成功登陆---");
                                    shoppingSettlementPage(salesMan.getSid());//参数为营业员编号sId
                                } else {
                                    System.err.println("\t!!密码错误!!\n");
                                    System.out.println("\n剩余登陆次数：" + loginNum);
                                }
                            }
                        }
                        System.out.println("------------------");
                        System.err.println("\t！！您已被强制退出系统！！");
                        System.exit(1);
                        break;
                    case 2:
                        /*System.out.println("------------------");
                        System.out.println("您已经退出系统!");
                        System.exit(-1);*/
                        MainPage.mainPage();
                        break;
                    default:
                        break;
                }
            }
            System.err.println("!输入有误!");
            System.out.println("重新输入或按 0 返回上一级菜单");
        }
    }

    /**
     * 商品管理界面
     *
     * @author celery
     * @version 1.0
     */
    public static void commdityManagementPage() {
        System.out.println("***************************\n");
        System.out.println("\t 1.售货员管理\n");
        System.out.println("\t 2.列出当日卖出列表\n");
        System.out.println("***************************");

        System.out.println("\n请输入选项,或者按 0 返回上一级菜单.");
        while (true) {
            String choice = scannerInfoString();
            String regex = "[0-2]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        mainPage();
                        break;
                    case 1:
                        salesManManagementPage();
                        break;
                    case 2:
                        SalesPage.dailySalesPage();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * 购物结算页面
     *
     * @param sid 售货员编号
     */
    public static void shoppingSettlementPage(int sid) {
        System.out.println("\n\t*******购物结算*******\n");
        while (true) {
            System.out.println("按 S 开始购物结算.按 0 返回账户登录界面");
            String choice = scannerInfoString();
            if (choice.equals("0")) {
                MainPage.cashierPage();
            } else if ((choice.equals("s")) || (choice.equals("S"))) {
                System.out.println("\n--请输入商品关键字--");
                int result = QueryPrint.querySettlement();
                switch (result) {
                    case 0:
                        System.err.println("\t！！查无此商品 ！！\n");
                        break;
                    case 1:
                        System.out.println("--按商品编号选择商品--");
                        int shoppingGid = scannerInfoInt();
                        ArrayList<Goods> goodsArrayList = QueryPrint.queryGoodsKey(shoppingGid, null);
                        if ((goodsArrayList == null) || (goodsArrayList.size() <= 0)) {
                            System.err.println("\t！！请按照查询结果输入正确编号 ！！\n");
                        } else {
                            Goods goods = goodsArrayList.get(0);
                            int gNum = goods.getGnum();
                            double gPrice = goods.getGprice();
                            System.out.println("--请输入购买数量--");
                            while (true) {
                                int shoppingNum = scannerInfoInt();
                                if (shoppingNum > gNum) {
                                    System.err.println("\t！！仓库储备不足！！");
                                    System.out.println("--请重新输入购买数量--");
                                } else {
                                    double totalPrice = gPrice * shoppingNum;
                                    System.out.println("\t\t\t  购物车结算\n");
                                    System.out.println("\t\t商品名称\t商品单价\t购买数量\t总价\n");
                                    System.out.println("\t\t"+goods.getGname()+"\t"+gPrice+" $\t"+shoppingNum+"\t"+totalPrice+" $\n");
                                    while (true) {
                                        System.out.println("确认购买：Y/N");
                                        String choShopping = scannerInfoString();
                                        if (("n".equals(choShopping)) || ("N".equals(choShopping))) {
                                            break;
                                        } else if (("y".equals(choShopping)) || ("Y".equals(choShopping))) {
                                            System.out.println("\n总价："+totalPrice+" $");
                                            System.out.println("\n输入实际缴费金额");
                                            while(true) {
                                                double recv = scannnerInfoDouble();
                                                if(recv < totalPrice) {
                                                    System.err.println("\t！！缴纳金额不足！！");
                                                    System.out.println("\n请重新输入缴纳金额($)");
                                                } else {
                                                    /*购物结算操作数据库*/
                                                    int goodsNewNum = goods.getGnum() - shoppingNum;
                                                    Goods newGoods = new Goods(goods.getGid(), goods.getGname(), goods.getGprice(), goodsNewNum);
                                                    boolean updateResult = GoodsDao.updateGoods(3, newGoods);   //key:3 更改商品数量
                                                    Gsales newSales = new Gsales(goods.getGid(), sid, shoppingNum);
                                                    boolean insertResult = GsalesDao.settlementGsales(newSales);
                                                    if(updateResult && insertResult) {
                                                        System.out.println("找零："+(recv - totalPrice));
                                                        System.out.println("\n谢谢光临，欢迎下次惠顾");
                                                    } else {
                                                        System.err.println("！支付失败！"); //出现这个错误一定是数据库操作有问题！
                                                    }
                                                }
                                                shoppingSettlementPage(sid);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    default:
                        break;
                }
            }
            System.err.println("\t!!请输入合法字符!!\n");
        }
    }

    public static void salesManManagementPage() {
        System.out.println("***************************\n");
        System.out.println("\t 1.添加售货员\n");
        System.out.println("\t 2.更改售货员\n");
        System.out.println("\t 3.删除售货员\n");
        System.out.println("\t 4.查询售货员\n");
        System.out.println("\t 5.显示所有售货员\n");
        System.out.println("***************************");

        System.out.println("\n请输入选项,或者按 0 返回上一级菜单.");
        while (true) {
            String choice = scannerInfoString();
            String regex = "[0-5]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        commdityManagementPage();
                        break;
                    case 1:
                        SalesManPage.addSalesManPage();
                        break;
                    case 2:
                        SalesManPage.updateSalesManPage();
                        break;
                    case 3:
                        SalesManPage.deleteSalesManPage();
                        break;
                    case 4:
                        SalesManPage.querySalesManPage();
                        break;
                    case 5:
                        SalesManPage.displaySalesManPage();
                        break;
                    default:
                        break;
                }
            }
            System.err.println("\t!输入有误!");
            System.out.println("重新输入或按 0 返回上一级菜单.");
        }
    }
}



