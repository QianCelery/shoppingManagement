package page;

import dao.GsalesDao;
import entity.Gsales;
import tools.ScannerChoice;

import java.util.ArrayList;

public final class SalesPage {
    /**
     * 列出当日售出商品
     */
    public static void dailySalesPage() {
        System.out.println("\t正在执行列出当日售出商品列表操作\n");
        ArrayList<Gsales> gsalesArrayList = GsalesDao.dailyGsales();
        if ((gsalesArrayList == null) || (gsalesArrayList.size() <= 0)) {
            System.err.println("\t！！今日无商品售出！！");
            MainPage.commdityManagementPage();
        } else {
            System.out.println("\t\t\t\t今日售出商品列表\n");
            System.out.println("\t商品名称\t\t商品价格\t\t商品数量\t\t销量\t\t备注\n");
            for (int i = 0, length = gsalesArrayList.size(); i < length; i++) {
                Gsales gsales = gsalesArrayList.get(i);
                System.out.print("\t" + gsales.getgName() + "\t\t" + gsales.getgPrice() + " $\t\t" + gsales.getgNum() + "\t\t" + gsales.getAllNum());
                int gNum = gsales.getgNum();
                if (gNum == 0) {
                    System.out.println("\t\t该商品已售空");
                } else if (gNum < 10) {
                    System.out.println("\t\t该商品已不足10件");
                } else {
                    System.out.println("\t\t-");
                }
                System.out.println("\t");
            }
            while (true) {
                System.out.println("\n\n输入 0 返回上一级菜单");
                String choice = ScannerChoice.scannerInfoString();
                if (choice.equals("0")) {
                    MainPage.commdityManagementPage();
                } else {
                    System.err.println("\t!!请输入合法字符!!\n");
                }

            }
        }
    }


}
