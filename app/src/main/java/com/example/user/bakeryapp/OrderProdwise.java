package com.example.user.bakeryapp;

/**
 * Created by User on 7/23/2017.
 */

public class OrderProdwise {
    String uname;
    String odate;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    String amount;

    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate;
    }
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }



    public String toString()
    {
        return "Username:"+uname+"  Price: "+amount+"  Date:"+odate;
    }

}
