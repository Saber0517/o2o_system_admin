package com.jyhon.serviceimpl;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jyhon.jms.PtpCosumer;
import com.jyhon.service.AuditService;
import com.oocl.jyhon.entiy.FoodEntity;
import com.oocl.jyhon.entiy.UserEntity;

import javax.jms.JMSException;
import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by WhiteSaber on 15/8/8.
 */
public class AuditSerivceImpl implements AuditService {
    private static PtpCosumer ptpCosumer = new PtpCosumer();
    private static Gson gson = new Gson();

    //不论是那种类型的请求，直接取出去之后判断
    public Map<String, Object> getRequest(ServletContext servletContext) {
        ptpCosumer.open();
        try {
            ptpCosumer.startListenServer();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        Map<String, Object> currentRequestMap = new LinkedHashMap<String, Object>();

            try {
                String message = ptpCosumer.getOneMessage();
                if (message.isEmpty()) {
                    currentRequestMap = Collections.emptyMap();
                } else {
                    JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
                    setRquestObjectMap(currentRequestMap, jsonObject);
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        return currentRequestMap;
    }

    private void setRquestObjectMap(Map<String, Object> currentRequestMap, JsonObject jsonObject) {
        if (jsonObject.has("FOOD")) {
            JsonElement foodString = jsonObject.get("FOOD");
            String foodJson = foodString.getAsString();
            FoodEntity foodEntity = gson.fromJson(foodJson,FoodEntity.class);
            if (null == currentRequestMap || currentRequestMap.isEmpty()) {
                currentRequestMap.put("requestFoodEntity",foodEntity);
            }else {
                currentRequestMap.put("requestFoodEntity", foodEntity);
            }
        } else if (jsonObject.has("USER")) {
            JsonElement foodString = jsonObject.get("USER");
            String userJson = foodString.getAsString();
            UserEntity userEntity = gson.fromJson(userJson, UserEntity.class);
            System.out.printf(userEntity.toString());
            if (null == currentRequestMap || currentRequestMap.isEmpty()) {
                currentRequestMap.put("requestUserEntity",userEntity);
            }else {

                currentRequestMap.put("requestUserEntity",userEntity);
            }
        }
    }
}
