package com.github.SakuraMatrix.p1lanchipham;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.SakuraMatrix.p1lanchipham.domain.Category;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reactor.netty.DisposableServer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) throws ClassNotFoundException, URISyntaxException {       
        log.info("Starting com.github.SakuraMatrix.p1-lanchipham...");

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        log.info("building disposable server...");
        appContext.getBean(DisposableServer.class).onDispose().block();
        appContext.close();
    }

    public static final ObjectMapper objMapper = new ObjectMapper();
    public static ByteBuf toByteBuf(Object obj) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            objMapper.writeValue(out, obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ByteBufAllocator.DEFAULT.buffer().writeBytes(out.toByteArray());
    }

    public static Category readCategory(String string) {
        Category category = null;
        try {
            category = objMapper.readValue(string, Category.class);
        } catch (JsonProcessingException jpe) {
            String[] params = string.split("&");
            int id = Integer.parseInt(params[0].split("=")[1]);
            String name = params[1].split("=")[1];
            double budget = Double.parseDouble(params[2].split("=")[1]);
            double alert = Double.parseDouble(params[3].split("=")[1]);
            double current = Double.parseDouble(params[4].split("=")[1]);
            String status = params[5].split("=")[1];
            category = new Category(id, name, budget, alert, current, status);
        }
        return category;
    }
}
