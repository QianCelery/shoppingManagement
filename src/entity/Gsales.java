package entity;

/**
 * gsales商品实体类
 *
 * @author celery
 * @version 1.0
 */
public final class Gsales {
    /*数据库键*/
    private int gsid;
    private int gid;
    private int sid;
    private String gName;
    private int sNum;
    private int gNum;
    private double gPrice;
    private int allNum;//单种商品销量总和

    /**
     * 购物结算
     *
     * @param gid  商品编号
     * @param sid  售货员编号
     * @param sNum 购买数量
     */
    public Gsales(int gid, int sid, int sNum) {
        this.gid = gid;
        this.sid = sid;
        this.sNum = sNum;
    }

    /**
     * 展示售出的商品
     * @param gName 商品名称
     * @param gPrice 商品价格
     * @param gNum 商品数量
     * @param allNum 商品售出总和
     */
    public Gsales(String gName, double gPrice, int gNum, int allNum) {
        this.gName = gName;
        this.gPrice = gPrice;
        this.gNum = gNum;
        this.allNum = allNum;
    }

    public int getGsid() {
        return gsid;
    }

    public void setGsid(int gsid) {
        this.gsid = gsid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getsNum() {
        return sNum;
    }

    public void setsNum(int sNum) {
        this.sNum = sNum;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public double getgPrice() {
        return gPrice;
    }

    public void setgPrice(double gPrice) {
        this.gPrice = gPrice;
    }

    public int getgNum() {
        return gNum;
    }

    public void setgNum(int gNum) {
        this.gNum = gNum;
    }

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }
}
