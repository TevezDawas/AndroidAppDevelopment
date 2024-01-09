package com.example.a1001_first;

import java.io.Serializable;
import java.util.ArrayList;

public class Company implements Serializable {

    public static int IDCompany =1;
    private String Id;
    private String Name;
    private String Email;
    private String Password;
    ArrayList<Coupon> Coupons =  new ArrayList<>();

    public Company(String id, String name, String email, String password) {
        Id = id+IDCompany;
        IDCompany++;
        Name = name;
        Email = email;
        Password = password;

    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public ArrayList<Coupon> getCoupons() {
        return Coupons;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setCoupons(ArrayList<Coupon> coupons) {
        Coupons = coupons;
    }

    @Override
    public String toString() {
        return "Company{" +
                "Id='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", Coupons=" + Coupons +
                '}';
    }



}
