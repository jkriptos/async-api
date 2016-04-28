package com.wu.docker;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Path("senders")
public class SenderApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIt() {
        return "{ firstname: 'Erick', lastname: 'Clapton', age: 45, rating: 4.3 }";
    }
}
