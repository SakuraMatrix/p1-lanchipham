package com.github.SakuraMatrix.p1lanchipham.domain;

import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        DisposableServer server =
                HttpServer.create()
                        .bindNow();

        server.onDispose()
                .block();
    }
}
