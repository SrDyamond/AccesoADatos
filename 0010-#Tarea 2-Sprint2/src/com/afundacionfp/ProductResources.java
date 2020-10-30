package com.afundacionfp;

import org.json.simple.JSONArray;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class ProductResources extends ServerResource {
    @Get
    public String Product() {
        DataProvider dataProvider = new MockDataProvider();

        // Creo un objeto JSONArray
        JSONArray jsonArray = new JSONArray();
        for (Product product: dataProvider.getProducts()) {
            jsonArray.add(product.toJSOn());
        }

        return jsonArray.toJSONString();
    }
}
