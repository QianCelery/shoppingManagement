package page;

import dao.SalesManDao;
import entity.SalesMan;
import tools.QueryPrint;
import tools.ScannerChoice;

import java.util.ArrayList;

public final class SalesManPage extends ScannerChoice {

    /**
     * 添加售货员页面
     */
    public static void addSalesManPage() {
        System.out.println("\t正在执行添加售货员操作\n");
        System.out.println("\n添加售货员-姓名");
        String sName = scannerInfoString();
        System.out.println("\n添加售货员-密码");
        String sPssswd = scannerInfoString();
        SalesMan salesMan = new SalesMan(sName, sPssswd);
        if (SalesManDao.addSalesMan(salesMan)) {
            System.out.println("\n\t!您已成功添加售货员到数据库!");
        } else {
            System.out.println("添加售货员失败");
        }
        changedInfoNext("addSalesManPage");
    }

    /**
     * 更改售货员信息页面
     */
    public static void updateSalesManPage() {
        System.out.println("\t正在执行更改售货员操作\n");
        System.out.println("请输入想要更改的售货员名字");
        String sName = scannerInfoString();
        ArrayList<SalesMan> salesManArrayList = QueryPrint.querySalesMan(sName);
        if ((salesManArrayList == null) || (salesManArrayList.size() <= 0)) {
            System.err.println("\t！！查无此人！！");
            changedInfoNext("updateSalesMan");
        } else {
            //显示将要更改的售货员信息
            System.out.println("\t\t\t售货员信息\n\n");
            System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");

            SalesMan salesMan = salesManArrayList.get(0); //上面的精确查找中，只能返回一组数值。无需遍历！
            System.out.println("\t" + salesMan.getSid() + "\t\t\t" + salesMan.getsName() + "\t\t\t" + salesMan.getsPassword());
            System.out.println();

            //选择更改售货员内容
            System.out.println("\n--------请选择您要更改的内容\n");
            System.out.println("\t1.更改售货员-姓名");
            System.out.println("\t2.更改售货员-密码");
            System.out.println("\n请输入选项,或者按 0 返回上一级菜单.");
            while (true) {
                String choice = scannerInfoString();
                String regex = "[1-2]";
                if (choice.matches(regex)) {
                    int info = Integer.parseInt(choice);
                    switch (info) {
                        case 0:
                            MainPage.salesManManagementPage();
                            break;
                        case 1:
                            System.out.println("更改售货员-新姓名");
                            String sNewName = scannerInfoString();
                            SalesMan salesManName = new SalesMan(sNewName, null);
                            if (SalesManDao.updateSalesMan(1, salesManName)) {
                                System.out.println("\n\t！！成功更新售货员名字至数据库！！\n");
                            } else {
                                System.err.println("\n\t！！更新售货员名字失敗！！");
                            }
                            changedInfoNext("updateSalesManPage");
                            break;
                        case 2:
                            System.out.println("更改售货员-新密码");
                            String sNewPasswd = scannerInfoString();
                            SalesMan salesManPassword = new SalesMan(null, sNewPasswd);
                            if (SalesManDao.updateSalesMan(2, salesManPassword)) {
                                System.out.println("\n\t！！成功更新售货员密码至数据库！！\n");
                            } else {
                                System.err.println("\n\t！！更新售货员密码失敗！！");
                            }
                        default:
                            break;
                    }
                }
                System.out.println("\t!输入有误!");
                System.out.println("\n请选择选项.或者按 0 返回上一级菜单.");
            }
        }
    }

    /**
     * 删除售货员页面
     */
    public static void deleteSalesManPage() {
        System.out.println("\t正在执行 删除售货员 操作\n");
        System.out.println("请输入想要删除的售货员名字");
        String sName = scannerInfoString();
        ArrayList<SalesMan> salesManArrayList = QueryPrint.querySalesMan(sName);
        if((salesManArrayList == null) || (salesManArrayList.size() <= 0)) {
            System.err.println("\t！！查无此人！！");
            changedInfoNext("updateSalesMan");
        } else {
            //显示将要删除的售货员信息
            System.out.println("\t\t\t删除售货员信息\n\n");
            System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");
            SalesMan salesMan = salesManArrayList.get(0); //上面的精确查找中，只能返回一组数值。无需遍历！
            System.out.println("\t" + salesMan.getSid() + "\t\t\t" + salesMan.getsName() + "\t\t\t" + salesMan.getsPassword());
            System.out.println();
            while (true) {
                System.out.println("\n确认删除该售货员：Y/N");
                String choice = scannerInfoString();
                if(("y".equals(choice)) || ("Y".equals(choice))) {
                    if(SalesManDao.deleteSalesMan(salesMan.getSid())) {
                        System.err.println("\t！！已成功刪除该售货员！！\n");
                    } else {
                        System.err.println("\t！！刪除该售货员失敗！！");
                    }
                    changedInfoNext("deleteSalesManPage");
                } else if (("n".equals(choice)) || ("N".equals(choice))){
                    MainPage.salesManManagementPage();
                } else {
                    System.err.println("\t!!输入有误,请重新输入!!");
                }
            }
        }
    }

    /**
     * 查询售货员页面
     */
    public static void querySalesManPage() {
        System.out.println("\t\t  正在执行查询售货员操作\n");
        System.out.println("要查询的售货员关键字");
        String sName = scannerInfoString();
        ArrayList<SalesMan> salesManArrayList = QueryPrint.querySalesMan(sName);
        if((salesManArrayList == null) || (salesManArrayList.size() <= 0)) {
            System.err.println("\t！没有人员符合查询条件！");
        } else {
            System.out.println("\t\t\t所有售货员列表\n\n");
            System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");
            for(int i = 0, length = salesManArrayList.size(); i < length; i++) {
                SalesMan salesMan = salesManArrayList.get(i);
                System.out.println("\t"+salesMan.getSid()+"\t\t\t"+salesMan.getsName()+"\t\t\t"+salesMan.getsPassword());
                System.out.println();
            }
        }
        changedInfoNext("querySalesManPage");
    }

    /**
     * 展示所有售货员页面
     */
    public static void displaySalesManPage() {
        ArrayList<SalesMan> salesManArrayList = SalesManDao.displaySalesMan();
        if ((salesManArrayList == null) || (salesManArrayList.size() <= 0)) {
            System.err.println("\t！！售货员列表为空！！");
            MainPage.salesManManagementPage();
        } else {
            System.out.println("\t\t\t所有售货员列表\n\n");
            System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");
            for (int i = 0, length = salesManArrayList.size(); i < length; i++) {
                SalesMan salesMan = salesManArrayList.get(i);
                System.out.println("\t" + salesMan.getSid() + "\t\t\t" + salesMan.getsName() + "\t\t\t" + salesMan.getsPassword());
                System.out.println();
            }
            while(true) {
                System.out.println("\n\n输入 0 返回上一级菜单");
                String choice = scannerInfoString();
                if ("0".equals(choice))
                {
                    MainPage.salesManManagementPage();
                }
                System.err.print("\t输入有误！");
            }
        }
    }
}
