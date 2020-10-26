package com.afundacionfp;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class HealthCheckResource extends ServerResource {
    @Get
    public String getHealth() {
        return "Health Check OK";
    }
}
