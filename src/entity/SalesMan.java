package entity;

/**
 * SalesMan 售货员实体类
 * @author celery
 * @version 1.0
 */
public final class SalesMan {
    /*数据库主键*/
    private int sid;
    private String sName;
    private String sPassword;

    public SalesMan(int sid, String sName, String sPassword) {
        this.sid = sid;
        this.sName = sName;
        this.sPassword = sPassword;
    }
    public SalesMan(String sName, String sPassword) {
        this.sName = sName;
        this.sPassword = sPassword;
    }
    public SalesMan(int sid, String sPassword) {
        this.sid = sid;
        this.sPassword = sPassword;
    }

    public int getSid(){
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }
}
