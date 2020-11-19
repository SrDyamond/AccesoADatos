package com.afundacionfp.resource;


import com.afundacionfp.DataProvider;
import com.afundacionfp.JDBCDataProvider;
import com.afundacionfp.Reserve;
import com.afundacionfp.resource.MockDataProvider;
import org.restlet.data.Form;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class ReserveResource extends ServerResource {
    @Get
    public String getReserve() {
        JDBCDataProvider dataProvider = new JDBCDataProvider();
        String username = (String) getRequest().getAttributes().get("username");
        String reference = (String) getRequest().getAttributes().get("reference");
        Form form = getRequest().getResourceRef().getQueryAsForm();

        String passwordSha = form.getFirstValue("passwordSha");
        if (passwordSha == null){
            return "No password provided";
        }

        return dataProvider.getReserve(reference,username,passwordSha).toJSOn().toString();
    }

    public String createReserve() {
        JDBCDataProvider dataProvider = new JDBCDataProvider();
        String username = (String) getRequest().getAttributes().get("username");
        String reference = (String) getRequest().getAttributes().get("reference");
        Form form = getRequest().getResourceRef().getQueryAsForm();

        String passwordSha = form.getFirstValue("passwordSha");
        if (passwordSha == null){
            return "No password provided";
        }


        return "Non implementado en esta version";
    }
    @Delete
    public  String removeReserve(){
        JDBCDataProvider dataProvider = new JDBCDataProvider();
        String username = (String) getRequest().getAttributes().get("username");
        String reference = (String) getRequest().getAttributes().get("reference");
        Form form = getRequest().getResourceRef().getQueryAsForm();

        String passwordSha = form.getFirstValue("passwordSha");
        if (passwordSha == null){
            return "No password provided";
        }
        return  "Non implementado en esta versión";
    }


}
