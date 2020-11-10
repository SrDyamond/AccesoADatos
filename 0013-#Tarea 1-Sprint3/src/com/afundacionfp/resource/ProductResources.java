package com.afundacionfp.resource;

import com.afundacionfp.DataProvider;
import com.afundacionfp.JDBCDataProvider;
import com.afundacionfp.Product;
import com.afundacionfp.resource.MockDataProvider;
import org.json.simple.JSONArray;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class ProductResources extends ServerResource {
    @Get
    public String getProducts() {
        JDBCDataProvider dataProvider = new JDBCDataProvider();
        JSONArray jsonArray = new JSONArray();
        for (Product product: dataProvider.getProducts()) {
            jsonArray.add(product.toJSOn());
        }
        return jsonArray.toJSONString();
    }
}
