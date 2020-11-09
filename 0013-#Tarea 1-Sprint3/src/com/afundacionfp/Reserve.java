package com.afundacionfp;

import netscape.javascript.JSObject;
import org.json.simple.JSONObject;

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

    public JSONObject toJSOn(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("product", this.product.toJSOn());
        jsonObject.put("reserveDate", this.reserveDate);
        return jsonObject;
    }
}