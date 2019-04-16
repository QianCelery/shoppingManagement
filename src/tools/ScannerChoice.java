package tools;

import page.GoodsPage;
import page.MainPage;
import page.SalesManPage;

import java.util.Scanner;

/**
 * 各种操作完成后下一步选择，界面操作选择
 *
 * @author celery
 * @version 1.0
 */
public class ScannerChoice {
    /**
     * 获取键盘输入
     *
     * @return String 键盘输入的数据
     */
    public static String scannerInfoString() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入：");
        return in.next();
    }

    public static double scannnerInfoDouble() {
        double num = 0.00;
        String regex = "(([1-9][0-9]*)\\.([0-9]{2}))|[0]\\.([0-9]{2})";//保留小数点后2位小数
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("请输入：");
            String choice = in.next();
            if (choice.matches(regex)) {
                num = Double.parseDouble(choice);
            } else {
                System.err.println("！输入有误！");
                continue;
            }
            break;
        }
        return num;
    }

    public static int scannerInfoInt() {
        int num = 0;
        String regex = "([1-9])|([1-9][0-9]+)";//商品数量
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("请输入：");
            String choice = in.next();
            if (choice.matches(regex)) {
                num = Integer.parseInt(choice);
            } else {
                System.err.println("！输入有误！");
                continue;
            }
            break;
        }
        return num;
    }

    /**
     * @param oper 调用者
     */
    public static void changedInfoNext(String oper) {
        System.out.println("是否继续进行-当前操作:(Y/N)");
        String choice = scannerInfoString();
        if (choice.equals("y") || choice.equals("Y")) {
            if (oper.equals("addGoodsPage")) {
                GoodsPage.addGoodsPage();
            } else if (oper.equals("upateGoodsPage")) {
                GoodsPage.updateGoodsPage();
            } else if (oper.equals("deleteGoodsPage")) {
                GoodsPage.deleteGoodsPage();
            } else if (oper.equals("queryGoodsPage")) {
                GoodsPage.queryGoodsPage();
            } else if (oper.equals("addSalesManPage")) {
                SalesManPage.addSalesManPage();
            } else if (oper.equals("updateSalesManPage")) {
                SalesManPage.updateSalesManPage();
            } else if (oper.equals("deleteSalesManPage")) {
                SalesManPage.deleteSalesManPage();
            } else if (oper.equals("querySalesManPage")) {
                SalesManPage.querySalesManPage();
            }
        } else if (choice.equals("n") || choice.equals("N")) {
            if (oper.equals("addGoodsPage")) {
                MainPage.maintenance();
            } else if (oper.equals("upateGoodsPage")) {
                MainPage.maintenance();
            } else if (oper.equals("deleteGoodsPage")) {
                MainPage.maintenance();
            } else if (oper.equals("queryGoodsPage")) {
                MainPage.maintenance();
            } else if (oper.equals("addSalesManPage")) {
                MainPage.salesManManagementPage();
            } else if (oper.equals("updateSalesManPage")) {
                MainPage.salesManManagementPage();
            } else if (oper.equals("deleteSalesManPage")) {
                MainPage.salesManManagementPage();
            } else if (oper.equals("querySalesManPage")) {
                MainPage.salesManManagementPage();
            }
        }else {
            System.out.println("\n输入有误！请重新输入.");
        }
    }
}
