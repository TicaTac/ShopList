package com.clm.shoplist;

/**
 * Created by jbt on 7/13/2016.
 */
public class Product {
    private String productName;
    private int productAmount;
    private boolean choose;

    public Product(String productName, int productAmount) {
        this.productName = productName;
        this.productAmount = productAmount;
    }

    public boolean isChosen() {

        return choose;
    }

    public void setChoose(boolean choose) {
        this.choose = choose;
    }



    public String getProductName() {

        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductAmmount() {
        return productAmount;
    }

    public void setProductAmmount(int productAmmount) {
        this.productAmount = productAmmount;
    }
}
