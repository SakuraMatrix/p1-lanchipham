package com.github.SakuraMatrix.p1lanchipham;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Budget{
    public static void main(String[] args) throws URISyntaxException {
       // Logger log = LoggerFactory.getLogger(Budget.class);
       // log.info("Starting com.github.SakuraMatrix.p1-lanchipham...");

        Path file = Paths.get(Budget.class.getResource("/catNotFound.html").toURI());
        //DisposableServer server =
                HttpServer.create()
                        //.port(8080)
                .route(routes->
                    routes.get("/categories", (req, res) ->
                        res.sendString(Mono.just("Here they are!")
                                .log("http-server")))
                        .get("/categories/{param}", (req, res) ->
                                res.sendString(Mono.just(req.param("Housing"))
                                        .log("http-server")))
                        .get("/error", (req, res) ->
                                res.status(404).addHeader("Message", "Goofed")
                                        .sendFile(file))
        )
                .bindNow().onDispose().block();
    }
}

