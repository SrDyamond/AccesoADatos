package com.afundacionfp.resource;


import com.afundacionfp.JDBCDataProvider;
import com.afundacionfp.exception.HttpExceptionCode;
import org.restlet.Response;
import org.restlet.data.Form;
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
    public Response createReserve() {
        String username = (String) getRequest().getAttributes().get("username");
        String reference = (String) getRequest().getAttributes().get("reference"); // obtenemos la referencia
        Form form = getRequest().getResourceRef().getQueryAsForm();
        String passwordSha = form.getFirstValue("passwordSha");
        JDBCDataProvider dataProvider = new JDBCDataProvider();
        Response response = getResponse();
        if (passwordSha == null) {
        }
        try {
            dataProvider.createReserve(reference, username, passwordSha);
        } catch (HttpExceptionCode e) {
            switch (e.getErrorCode()) {
                case 201:
                    response.setStatus(Status.SUCCESS_CREATED);
                    return response;
                case 401:
                    response.setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
                    return response;
                case 404:
                    response.setStatus(Status.CLIENT_ERROR_NOT_FOUND);
                    return response;
                case 418:
                    response.setStatus(Status.SERVER_ERROR_SERVICE_UNAVAILABLE);
                case 500:
                    response.setStatus(Status.SERVER_ERROR_INTERNAL);
                    return response;
            }
        }
        return response;
    }



    @Delete
    public  Response removeReserve() {
        JDBCDataProvider dataProvider = new JDBCDataProvider();
        String username = (String) getRequest().getAttributes().get("username");
        String reference = (String) getRequest().getAttributes().get("reference");
        Form form = getRequest().getResourceRef().getQueryAsForm();
        String passwordSha = form.getFirstValue("passwordSha");
        Response response = getResponse();
        if (passwordSha == null) {
        }
        try {
            dataProvider.removeReserve(reference, username, passwordSha);
        } catch (HttpExceptionCode e) {
            switch (e.getErrorCode()) {
                case 200:
                    response.setStatus(Status.SUCCESS_OK);
                    return response;
                case 204:
                    response.setStatus(Status.SUCCESS_NO_CONTENT);
                case 401:
                    response.setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
                    return response;
                case 404:
                    response.setStatus(Status.CLIENT_ERROR_NOT_FOUND);
                    return response;
                case 418:
                    response.setStatus(Status.SERVER_ERROR_SERVICE_UNAVAILABLE);
                case 500:
                    response.setStatus(Status.SERVER_ERROR_INTERNAL);
                    return response;
            }

        }
        return response;
    }


}
