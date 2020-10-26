package com.afundacionfp;

public class ProductInfo {

        private String description;
        private int price;
        private int availableAmount;

    public ProductInfo(String description, int price, int availableAmount) {
        this.description = description;
        this.price = price;
        this.availableAmount = availableAmount;
    }

    public String getDescription() {
            return description;
        }
        public int getPrice() {
            return price;
        }

        public int getAvailableAmount() {
            return availableAmount;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setAvailableAmount(int availableAmount) {
            this.availableAmount = availableAmount;
        }

}
