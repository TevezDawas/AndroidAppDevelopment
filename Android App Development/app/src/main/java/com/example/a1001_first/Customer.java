package com.example.a1001_first;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
    public static int IDCustomer;
    private  String Id;
    private String FIRST_NAME;
    private String LAST_NAME;
    private String EMAIL;
    private String PASSWORD;
    ArrayList<Coupon> Coupons = new ArrayList<>();

    public Customer(String iD, String firstName, String lastName, String eMail, String password) {
        this.Id = iD + IDCustomer;
        IDCustomer++;
        this.FIRST_NAME = firstName;
        this.LAST_NAME = lastName;
        this.EMAIL = eMail;
        this.PASSWORD = password;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }


    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "Id='" + Id + '\'' +
                ", FIRST_NAME='" + FIRST_NAME + '\'' +
                ", LAST_NAME='" + LAST_NAME + '\'' +
                ", EMAIL='" + EMAIL + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", Coupons=" + Coupons +
                '}';
    }
}
