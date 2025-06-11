package com.pluralsight.models;

public class Product {
    private int ID;
    private String productName;
    private int categoryID;
    private int supplierID;
    private double price;

    public Product(int ID, String productName, int supplierID, int categoryID, double price) {
        this.ID = ID;
        this.productName = productName;
        this.categoryID = categoryID;
        this.supplierID = supplierID;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return ID + " " + productName + " (" + price + ")";
    }
}
