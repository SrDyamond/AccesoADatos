package com.afundacionfp.resource;

import com.afundacionfp.DataProvider;
import com.afundacionfp.JDBCDataProvider;
import com.afundacionfp.resource.MockDataProvider;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class FullProductResources extends ServerResource {
    @Get
    public String getFullProducts() {

        JDBCDataProvider dataProvider = new JDBCDataProvider();
        //Meto en la variable referencia el valor del parametro
        String reference = (String) getRequest().getAttributes().get("reference");

        return dataProvider.getFullProduct(reference).toJSOn().toJSONString();
    }
}
