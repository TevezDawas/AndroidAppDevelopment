package com.example.a1001_first;


import java.io.Serializable;

public class Coupon  implements Serializable {

    public static int IDCoupon=1;
    private String Id;
    private String companyName;
    private String category;
    private String title;
    private String Description;
    private String StartDate;
    private String EndDate;
    private int AMOUNT;
    private Double PRICE;
    private String IMAGE;

    public Coupon(String id, String CompanyId, String category, String title,
                  String description, String startDate, String endDate, Integer AMOUNT, Double PRICE, String IMAGE) {
        Id = id + IDCoupon;
        IDCoupon++;
        companyName = CompanyId;
        this.category = category;
        this.title = title;
        Description = description;
        StartDate = startDate;
        EndDate = endDate;
        this.AMOUNT = AMOUNT;
        this.PRICE = PRICE;
        this.IMAGE = IMAGE;
    }

    public String getId() {
        return Id;
    }

    public String getCompany() {
        return companyName;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return Description;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public int getAMOUNT() {
        return AMOUNT;
    }

    public Double getPRICE() {
        return PRICE;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setCompany(String companyname) {
        companyName = companyname;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public void setAMOUNT(int AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public void setPRICE(Double PRICE) {
        this.PRICE = PRICE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "Id='" + Id + '\'' +
                ", Companyid='" +companyName + '\'' +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", Description='" + Description + '\'' +
                ", StartDate='" + StartDate + '\'' +
                ", EndDate='" + EndDate + '\'' +
                ", AMOUNT=" + AMOUNT +
                ", PRICE=" + PRICE +
                ", IMAGE='" + IMAGE + '\'' +
                '}';
    }
}

