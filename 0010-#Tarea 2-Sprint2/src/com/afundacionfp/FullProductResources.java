package com.afundacionfp;

import org.json.simple.JSONArray;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class FullProductResources extends ServerResource {
    @Get
    public String getFullProducts() {

        DataProvider dataProvider = new MockDataProvider();
        //Meto en la variable referencia el valor del parametro
        String reference = (String) getRequest().getAttributes().get("reference");
        // Creo un objeto JSONArray
        JSONArray jsonArray = new JSONArray();
       for (Product product: dataProvider.getFullProduct()) {
         jsonArray.add(product.toJSOn());
       }
        return jsonArray.toJSONString();
    }
}
