package com.example.user.bakeryapp.bakery.dbutil;

/**
 * Created by User on 7/14/2017.
 */

public class BakeryConstants
{

    public static final String DB_NAME="bakery";


    public static final int DB_VERSION=4;
    public static final String CAT_TBL_NAME="category";
    public static final String CAT_COL_ID="cid";
    public static final String CAT_COL_NAME="cname";

    public static final String PRO_TBL_NAME="product";
    public static final String PRO_COL_CAT_ID="cid";
    public static final String PRO_COL_ID="pid";
    public static final String PRO_COL_NAME="pname";
    public static final String PRO_COL_PRICE="price";

    public static final String USER_TBL_NAME="user";
    public static final String USER_COL_ID="userid";
    public static final String USER_COL_NAME="username";
    public static final String USER_COL_PHONE="phone";
    public static final String USER_COL_EMAIL="email";

    public static final String ORDER_TBL_NAME="myorder";
    public static final String ORDER_COL_USERNAME="username";
    public static final String ORDER_COL_NAME="pname";
    public static final String ORDER_COL_DATE="date";
    public static final String ORDER_COL_AMOUNT="amount";

    public static final
    String CAT_TBL_QUERY="create table "+CAT_TBL_NAME+"("+CAT_COL_ID+" integer primary key autoincrement,"+CAT_COL_NAME+" text)";

    public static final
    String PRO_TBL_QUERY="create table "+PRO_TBL_NAME+"("+PRO_COL_CAT_ID+" integer,"+PRO_COL_ID+" integer primary key autoincrement,"+PRO_COL_NAME+" text,"+PRO_COL_PRICE+" integer,foreign key("+PRO_COL_CAT_ID+") references "+CAT_TBL_NAME+"("+CAT_COL_ID+"))";

    public static final
    String USER_TBL_QUERY="create table "+USER_TBL_NAME+"("+USER_COL_ID+" integer primary key autoincrement,"+USER_COL_NAME+" text,"+USER_COL_PHONE+" text,"+USER_COL_EMAIL+" text)";

    public static final
    String ORDER_TBL_QUERY="create table "+ORDER_TBL_NAME+"("+ORDER_COL_USERNAME+" text,"+ORDER_COL_NAME+" text,"+ORDER_COL_DATE+" text,"+ORDER_COL_AMOUNT+" integer)";





}
