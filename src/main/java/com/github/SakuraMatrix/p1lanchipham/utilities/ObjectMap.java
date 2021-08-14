package com.github.SakuraMatrix.p1lanchipham.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.SakuraMatrix.p1lanchipham.domain.Category;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ObjectMap {
    static final ObjectMapper objMapper = new ObjectMapper();
    static ByteBuf toByteBuf(Object obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            objMapper.writeValue(baos, obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ByteBufAllocator.DEFAULT.buffer().writeBytes(baos.toByteArray());
    }

    static Category readCategory(String string) {
        Category category = null;
        try {
            category = objMapper.readValue(string, Category.class);
        } catch (JsonProcessingException jpe) {
            String[] params = string.split("&");
            int id = Integer.parseInt(params[0].split("=")[1]);
            String name = params[1].split("=")[1];
            double budget = Double.parseDouble(params[2].split("=")[1]);
            double alert = Double.parseDouble(params[3].split("=")[1]);
            double currentUse = Double.parseDouble(params[4].split("=")[1]);
            String status = params[5].split("=")[1];
            category = new Category(id, name, budget, alert, currentUse, status);
        }
        return category;
    }
}
