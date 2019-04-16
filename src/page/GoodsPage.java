package page;

import dao.GoodsDao;
import entity.Goods;
import tools.QueryPrint;
import tools.ScannerChoice;

import java.util.ArrayList;

public class GoodsPage extends ScannerChoice {
    /**
     * 添加商品
     */
    public static void addGoodsPage() {
        System.out.println("\t正在执行添加商品操作\n");

        System.out.println("\n請輸入添加商品-名称");
        String gname = scannerInfoString();
        System.out.println("\n請輸入添加商品-价格");
        double gprice = Double.parseDouble(scannerInfoString());
        System.out.println("\n請輸入添加商品-数量");
        int gnum = Integer.parseInt(scannerInfoString());
        Goods goods = new Goods(gname, gprice, gnum);
        boolean bool = GoodsDao.addGoods(goods);
        if (bool) {
            System.out.println("\n\t!您已成功添加商品到数据库!");
        } else {
            System.out.println("添加商品失败");
        }
        changedInfoNext("addGoodsPage");
    }

    /**
     * 更改商品信息
     */
    public static void updateGoodsPage() {
        System.out.println("\t正在执行 更改商品 操作\n");
        System.out.println("请输入想要更改的商品名字");
        int gid = QueryPrint.query("updateGoodsPage");
        System.out.println("\n--------请选择您要更改的内容\n");
        System.out.println("\t1.更改商品-名称");
        System.out.println("\t2.更改商品-价格");
        System.out.println("\t3.更改商品-数量");
        System.out.println("\n请输入选项,或者按0返回上一级菜单.");
        while (true) {
            String choice = scannerInfoString();
            String regex = "[0-3]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        MainPage.maintenance();
                        break;
                    case 1:
                        System.out.println("请输入商品-新名称");
                        String gname = scannerInfoString();
                        Goods goodsName = new Goods(gid, gname);
                        boolean boolName = GoodsDao.updateGoods(info, goodsName);
                        if (boolName) {
                            System.out.println("\n\t！！成功更新商品名至数据库！！\n");
                        } else {
                            System.err.println("\n\t！！更新商品名失敗！！");
                        }
                        changedInfoNext("updateGoodsPage");
                        break;
                    case 2:
                        System.out.println("请输入商品-新价格");
                        double gprice = scannnerInfoDouble();
                        Goods goodsPrice = new Goods(gid, gprice);
                        boolean boolPrice = GoodsDao.updateGoods(info, goodsPrice);
                        if (boolPrice) {
                            System.out.println("\n\t！！成功更新商品名至数据库！！\n");
                        } else {
                            System.err.println("\n\t！！更新商品名失敗！！");
                        }
                        changedInfoNext("upateGoodsPage");
                        break;
                    case 3:
                        System.out.println("请输入商品-新数量");
                        int gnum = scannerInfoInt();
                        Goods goodsNum = new Goods(gid, gnum);
                        boolean boolNum = GoodsDao.updateGoods(info, goodsNum);
                        if (boolNum) {
                            System.out.println("\n\t！！成功更新商品名至数据库！！\n");
                        } else {
                            System.err.println("\n\t！！更新商品名失敗！！");
                        }
                        changedInfoNext("upateGoodsPage");
                        break;
                    default:
                        break;
                }
            }
            System.err.println("！输入有误！");
            System.out.println("请重新选择,或者按0返回上一级菜单.");
        }

    }

    /**
     * 删除商品
     */
    public static void deleteGoodsPage() {
        System.out.println("\t正在执行 删除商品 操作\n");
        System.out.println("请输入想要删除的商品名字");
        int gid = QueryPrint.query("deleteGoodsPage");
        while (true) {
            System.out.println("\n确认删除该商品：Y/N");
            String choice = scannerInfoString();
            if (choice.equals("y") || choice.equals("Y")) {
                boolean bool = GoodsDao.deleteGoods(gid);
                if (bool) {
                    System.err.println("\t！！已成功刪除该商品！！\n");
                } else {
                    System.err.println("\n\t！！刪除该商品失敗！！");
                }
                changedInfoNext("deleteGoodsPage");
            }
            System.out.println("\t!!输入有误,请重新输入!!\n");
        }
    }

    /**
     * 查询商品
     */
    public static void queryGoodsPage() {
        System.out.println("\t\t  正在执行查询商品操作\n");
        System.out.println("\t\t1.按照商品 数量升序 查询");
        System.out.println("\t\t2.按照商品 价格升序 查询");
        System.out.println("\t\t3.输入商品  关键字  查询");
        System.out.println("\n请输入选项,或者按0返回上一级菜单.");
        while (true) {
            String choice = scannerInfoString();
            String regex = "[0-3]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                    case 0:
                        MainPage.maintenance();
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        System.out.println("\t\t正在执行商品  关键字  查询操作\n");
                        System.out.println("\n请输入商品关键字");
                        break;
                    default:
                        break;

                }
                ArrayList<Goods> goodslist = GoodsDao.queryGoods(info);
                if((goodslist == null) || goodslist.size() <= 0) {
                    System.err.println("\n\t!!您查询的商品不存在!!\n");
                    queryGoodsPage();
                } else {
                    if (info == 1) //打印目录，不要放在功能函数中，会影响其他方法调用
                    {
                        System.out.println("\t\t\t\t\t商品按照 数量升序 列表\n\n");
                    }else if (info == 2)
                    {
                        System.out.println("\t\t\t\t\t商品按照 价格升序 列表\n\n");
                    }else
                    {
                        System.out.println("\t\t\t\t\t您所查找的商品如下\n\n");
                    }
                    System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
                }
                for (int i = 0, length = goodslist.size(); i < length; i++) {
                    Goods goods = goodslist.get(i);
                    System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+"\t\t"+goods.getGnum());
                    int gnum = goods.getGid();
                    if(goods.getGnum() == 0) {
                        System.out.println("\t\t该商品已售空");
                    } else if (goods.getGnum() <= 10){
                        System.out.println("\t\t该商品已不足10件");
                    } else {
                        System.out.println("\t\t商品库存正常");
                    }
                    System.out.println("---------------------");
                }
                while (true) {
                    System.out.println("输入 0 返回上一级菜单");
                    String choiceNext = ScannerChoice.scannerInfoString();
                    if("0".equals(choiceNext)) {
                        MainPage.maintenance();
                    }
                    System.out.println("输入有误！");
                }
            }
            System.err.println("输入有误！");
            System.out.println("请重新选择,或者按0返回上一级菜单.");
        }
    }

    /**
     * 展示所有商品
     */
    public static void displayGoodsPage() {
        System.out.println("\t\t\t\t\t所有商品列表\n\n");
        ArrayList<Goods> goodslist = GoodsDao.displayGoods();
        if((goodslist == null) || (goodslist.size() <= 0)) {
            System.err.println("！库存为空！");
            MainPage.maintenance();
        } else {
            System.out.println("\t商品编号\t\t商品名称\t\t商品价格\t\t商品数量\t\t备注\n");
            for (int i = 0, length = goodslist.size(); i < length; i++) {
                Goods goods = goodslist.get(i);
                System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+"\t\t"+goods.getGnum());
                int gnum = goods.getGid();
                if(goods.getGnum() == 0) {
                    System.out.println("\t\t该商品已售空");
                } else if (goods.getGnum() <= 10){
                    System.out.println("\t\t该商品已不足10件");
                } else {
                    System.out.println("\t\t商品库存正常");
                }
                System.out.println("---------------------");
            }
            while (true) {
                System.out.println("输入 0 返回上一级菜单");
                String choiceNext = ScannerChoice.scannerInfoString();
                if("0".equals(choiceNext)) {
                    MainPage.maintenance();
                }
                System.out.println("输入有误！");
            }
        }
    }
}
