package com.afundacionfp;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class FullProductResources extends ServerResource {
    @Get
    public String getFullProducts() {

        DataProvider dataProvider = new MockDataProvider();
        //Meto en la variable referencia el valor del parametro
        String reference = (String) getRequest().getAttributes().get("reference");

        return dataProvider.getFullProduct(reference).toJSOn().toJSONString();
    }
}
