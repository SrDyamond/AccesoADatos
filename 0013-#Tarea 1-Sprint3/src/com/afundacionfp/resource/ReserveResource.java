package com.afundacionfp.resource;


import com.afundacionfp.DataProvider;
import com.afundacionfp.JDBCDataProvider;
import com.afundacionfp.Reserve;
import com.afundacionfp.resource.MockDataProvider;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
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
    @Post
    public void postReserve() {
        String username = (String) getRequest().getAttributes().get("username");
        String reference = (String) getRequest().getAttributes().get("reference"); // obtenemos la referencia

        Form form = getRequest().getResourceRef().getQueryAsForm();
        String passwordSha = form.getFirstValue("passwordSha");
        Response response = getResponse();
        JDBCDataProvider dataProvider = new JDBCDataProvider();
        if (passwordSha == null){
        }
        /*
        try {
            dataProvider.createReserve(reference, username, passwordSha);
        } catch (HttpExceptionCode e) {
            switch (e.getErrorCode()) {
                case 200:
                    response.setStatus(Status.SUCCESS_OK);
                    return response;
                case 401:
                    response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
                    return response;
                case 404:
                    response.setStatus(Status.CLIENT_ERROR_CONFLICT);
                    return response;
                case 500:
                    response.setStatus(Status.CLIENT_ERROR_FORBIDDEN);
                    return response;
            }
        }


        return getResponse();

         */
    }


    @Delete
    public  void removeReserve(){
        JDBCDataProvider dataProvider = new JDBCDataProvider();
        String username = (String) getRequest().getAttributes().get("username");
        String reference = (String) getRequest().getAttributes().get("reference");
        Form form = getRequest().getResourceRef().getQueryAsForm();

        String passwordSha = form.getFirstValue("passwordSha");
        if (passwordSha == null){
        }

    }


}
