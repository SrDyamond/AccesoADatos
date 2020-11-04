package com.afundacionfp;

import org.restlet.*;
import org.restlet.data.Protocol;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.routing.Router;

public class Main extends Application {

    public Main(Context context) {
        super(context);
    }

    public static void main(String[] args) throws Exception {
        final Component component = new Component();
        component.getServers().add(Protocol.HTTP, 8080);
        Main server = new Main(component.getContext().createChildContext());
        component.getDefaultHost().attach(server);
        component.start();
    }

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext().createChildContext());
        router.attach("/product", ProductResources.class);
        router.attach("/product/{reference}", FullProductResources.class);
        router.attach("/{username}/reserve/{reference}", ReserveResource.class);
        return router;
    }


}