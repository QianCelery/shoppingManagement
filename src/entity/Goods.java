package entity;

/**
 * Goods商品实体类
 *
 * @author celery
 * @version 1.0
 */
public class Goods {
    /*数据库主键*/
    private int gid;
    private String gname;
    private double gprice;
    private int gnum;

    /**
     * 添加商品信息
     */
    public Goods(String gname, double gprice, int gnum) {
        this.gname = gname;
        this.gprice = gprice;
        this.gnum = gnum;
    }

    public Goods(int gid, String gname, double gprice, int gnum) {
        this.gid = gid;
        this.gname = gname;
        this.gprice = gprice;
        this.gnum = gnum;
    }

    public Goods(int gid, String gname) {
        this.gid = gid;
        this.gname = gname;
    }

    public Goods(int gid, double gprice) {
        this.gid = gid;
        this.gprice = gprice;
    }

    public Goods(int gid, int gnum) {
        this.gid = gid;
        this.gnum = gnum;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public double getGprice() {
        return gprice;
    }

    public void setGprice(double gprice) {
        this.gprice = gprice;
    }

    public int getGnum() {
        return gnum;
    }

    public void setGnum(int gnum) {
        this.gnum = gnum;
    }
}
