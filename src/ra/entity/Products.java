package ra.entity;

import java.util.Date;
import java.util.Scanner;

public class Products {
    private int productId;
    private String productName;
    private int stock;
    private double costPrice;
    private double sellingPrice;
    private Date createdAt;
    private int catId;
    public Products() {

    }

    public Products(int productId, String productName, int stock, double costPrice, double sellingPrice, Date createdAt, int catId) {
        this.productId = productId;
        this.productName = productName;
        this.stock = stock;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.createdAt = createdAt;
        this.catId = catId;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getCostPrice() {
        return this.costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellingPrice() {
        return this.sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getCatId() {
        return this.catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }
    public void inputData(Scanner scanner) {

    }


    @Override
    public String toString() {
        return "Products{" +
                "Mã SP=" + this.productId +
                ", Tên SP='" + this.productName + '\'' +
                ", Số lượng SP=" + this.stock +
                ", Giá nhập=" + this.costPrice +
                ", Giá xuất=" + this.sellingPrice +
                ", Ngày tạo=" + this.createdAt +
                ", Mã danh mục=" + this.catId +
                '}';
    }
}
