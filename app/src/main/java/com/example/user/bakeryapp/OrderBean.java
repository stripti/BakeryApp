package com.example.user.bakeryapp;

/**
 * Created by User on 7/23/2017.
 */

public class OrderBean
{
    String uname,prodname;
    int amount;


    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate;
    }

    String odate;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String toString()
    {
        return "Username:"+uname+"  Product name:"+prodname+"  Amount:"+amount;
    }
}
