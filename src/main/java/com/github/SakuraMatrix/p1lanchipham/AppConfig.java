package com.github.SakuraMatrix.p1lanchipham;

import com.datastax.oss.driver.api.core.CqlSession;
import com.github.SakuraMatrix.p1lanchipham.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@ComponentScan
public class AppConfig {

        @Autowired CategoryService categoryService;

        @Bean
        public CqlSession session() {
                return CqlSession.builder().build();
        }

        @Bean
        public DisposableServer server() throws URISyntaxException {
                Path indexHTML = Paths.get(App.class.getResource("/index.html").toURI());
                Path errorHTML = Paths.get(App.class.getResource("/error.html").toURI());

                return HttpServer.create()
                        .port(8080)
                        .route(routes ->
                                routes.get("/categories", (req, res) ->
                                                res.send(categoryService.getAll()
                                                        .map(App::toByteBuf)
                                                        .log("http-server")))
                                        .get("/categories/{id}", (req, res) ->
                                                res.send(categoryService.get(req.param("id"))
                                                        .map(App::toByteBuf)
                                                        .log("http-server")))
                                        .put("/categories/{id}", (req, res) ->
                                                res.send(req.receive().asString()
                                                        .map(App::readCategory)
                                                        .map(categoryService::setInitialValues)
                                                        .map(App::toByteBuf)
                                                        .log("http-server")))
                                        .put("/categories/{id}", (req, res) ->
                                                res.send(req.receive().asString()
                                                        .map(App::readCategory)
                                                        .map(categoryService::updateCurrentUse)
                                                        .map(App::toByteBuf)
                                                        .log("http-server")))
                                        .get("/", (req, res) ->
                                                res.sendFile(indexHTML))
                                        .get("/error", (req, res) ->
                                                res.status(404).addHeader("Message", "Aw, don't cry. It's just an error.")
                                                        .sendFile(errorHTML)))
                        .bindNow();
        }
}
