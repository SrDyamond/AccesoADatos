package com.afundacionfp;

public class Product {
    private String name;
    private String reference;
    private String imagePath;
    private ProductInfo productInfo;

    public Product(String name, String reference, String imagePath, ProductInfo productInfo) {
        this.name = name;
        this.reference = reference;
        this.imagePath = imagePath;
        this.productInfo = productInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }
}
