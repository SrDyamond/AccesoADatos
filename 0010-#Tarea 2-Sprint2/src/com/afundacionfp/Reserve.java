package com.afundacionfp;

public class Reserve {
    private Product product;
    private long reserveDate;

    public Reserve(Product product, long reserveDate) {
        this.product = product;
        this.reserveDate = reserveDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(long reserveDate) {
        this.reserveDate = reserveDate;
    }
}