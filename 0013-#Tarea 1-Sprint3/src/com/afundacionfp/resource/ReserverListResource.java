package com.afundacionfp.resource;

import com.afundacionfp.DataProvider;
import com.afundacionfp.JDBCDataProvider;
import com.afundacionfp.Reserve;
import org.json.simple.JSONArray;
import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.util.List;

public class ReserverListResource extends ServerResource {
    @Get
    public String getReserve() {
        DataProvider dataProvider = new JDBCDataProvider();
        // Obtenemos el nombre de usuario de la ruta. Por ejemplo, si navegas
        // a http://localhost:8080/developer/reserves?passwordSha=xxxx, el
        // nombre de usuario será "developer".
        // Esto lo hemos especificado nosotros en la sentencia router.attach(...)
        String username = (String) getRequest().getAttributes().get("username");
        // Usamos las siguientes líneas para obtener el query param passwordSha
        Form form = getRequest().getResourceRef().getQueryAsForm();
        String passwordSha = form.getFirstValue("passwordSha");
        if (passwordSha == null) {
            return "No password";
        }
        JSONArray jsonArray = new JSONArray();
        List<Reserve> listaReservas = dataProvider.getReserves(username, passwordSha);
        for (Reserve reserve: listaReservas) {
            jsonArray.add(reserve.toJSOn());
        }
        return jsonArray.toJSONString();
    }

}
