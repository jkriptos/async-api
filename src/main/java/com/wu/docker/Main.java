package com.wu.docker;

import com.google.common.base.Optional;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {
    public static final String BASE_URI;
    public static final String protocol;
    public static final Optional<String> host;
    public static final String path;
    public static final Optional<String> port;

    static{
        protocol = "http://";
        host = Optional.fromNullable(System.getenv("HOSTNAME"));
        port = Optional.fromNullable(System.getenv("PORT"));
        path = "wuapi";
        BASE_URI = protocol + host.or("localhost") + ":" + port.or("8080") + "/" + path + "/";
    }

    // Base URI the Grizzly HTTP server will listen on
    //public static final String BASE_URI = "http://0.0.0.0:9001/myapp/";

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("com.wu.docker");

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.shutdownNow();
    }
}

