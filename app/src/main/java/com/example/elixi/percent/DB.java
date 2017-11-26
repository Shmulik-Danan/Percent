package com.example.elixi.percent;

/**
 * Created by elixi on 22 אוקטובר 2017.
 */

public class DB {
    private String p1,p2,totall,price;

    public DB(String price,String p1, String totall ) {

        this.p1 = p1;
        this.p2 = "";
        this.totall = totall;
        this.price = price;
    }
    public DB(String price,String p1, String p2, String totall ) {
        this.p1 = p1;
        this.p2 = p2;
        this.totall = totall;
        this.price = price;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getTotall() {
        return totall;
    }

    public void setTotall(String totall) {
        this.totall = totall;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DB{" +
                "p1='" + p1 + '\'' +
                ", p2='" + p2 + '\'' +
                ", totall='" + totall + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
