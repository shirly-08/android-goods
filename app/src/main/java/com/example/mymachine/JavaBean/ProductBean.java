package com.example.mymachine.JavaBean;

public class ProductBean {
    /**
     * 创建product类（6个）,封装
     */

    private String pid;
    private String pname;
    private String image;
    private String pnum;
    private String snum;
    private String price;
    public ProductBean(String pid, String pname, String image, String pnum, String snum, String price) {
        this.pid = pid;
        this.pname = pname;
        this.image = image;
        this.pnum = pnum;
        this.snum = snum;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", image='" + image + '\'' +
                ", pnum='" + pnum + '\'' +
                ", snum='" + snum + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPnum() {
        return pnum;
    }

    public void setPnum(String pnum) {
        this.pnum = pnum;
    }

    public String getSnum() {
        return snum;
    }

    public void setSnum(String snum) {
        this.snum = snum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



}
