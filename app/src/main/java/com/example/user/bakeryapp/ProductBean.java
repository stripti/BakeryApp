package com.example.user.bakeryapp;

/**
 * Created by User on 7/15/2017.
 */

public class ProductBean {
    int cid,pid,price;
    String pname;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
    public String toString()
    {
        return pname+"   "+price+"Rs.";
    }
}
