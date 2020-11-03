package com.afundacionfp;


import org.restlet.data.Form;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class ReserveResource extends ServerResource {
    @Get
    public String getReserve() {
        DataProvider dataProvider = new MockDataProvider();
        String username = (String) getRequest().getAttributes().get("username");
        String reference = (String) getRequest().getAttributes().get("reference");
        Form form = getRequest().getResourceRef().getQueryAsForm();
        String passwordSha = form.getFirstValue("passwordSha");
        if (passwordSha == null){
            return "No password provided";
        }

        return dataProvider.getReserve(reference,username,passwordSha).toJSOn().toString();
    }
    /*
    @Post
    public String postReserve(){
        return "No implementado en esta versión";
    }
    @Delete
    public  String deleteReserve(){
        return  "Non implementado en esta versión";
    }
    */

}
