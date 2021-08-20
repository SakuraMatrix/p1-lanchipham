package com.github.SakuraMatrix.p1lanchipham;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.SakuraMatrix.p1lanchipham.domain.Category;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reactor.netty.DisposableServer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * The Budget Reminder is an application which allows a user to
 * set up a budget amount for each expense category. It also lets 
 * the user set up alert amounts and a status update to warn her/her 
 * when s/he is close to the budget limit. 
 * 
 * @author LanChi
 * @version 1.0
 * @since 08-06-2021
 */
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) throws ClassNotFoundException, URISyntaxException, JsonProcessingException, JsonMappingException {       
        log.info("Starting com.github.SakuraMatrix.p1-lanchipham...");

        /**
         * instantiating a Spring IoC container using ApplicationContext
         */
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        log.info("building disposable server...");
        appContext.getBean(DisposableServer.class).onDispose().block();
        appContext.close();
        //String str = "{\"id\": 9, \"name\": \"eating out\", \"budget\": 100, \"alert\": 80, \"current\": 75, \"status\": \"good\"}";
        //System.out.println(readCategory(str));
        // Category category = objMapper.readValue(str, Category.class);
        // System.out.println(category);
    }

    /**method to take in and write out an object's byte array */
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

    /**method to split the string of the get url and 
     * place each component into its own variable */
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
