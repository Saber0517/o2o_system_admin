package com.jyhon.serviceimpl;


import com.jyhon.service.FoodEntityService;
import com.oocl.jyhon.daoimple.FoodEntityDaoImple;
import com.oocl.jyhon.entiy.FoodEntity;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZHANGJA4 on 8/7/2015.
 */
public class FoodEntityServiceImpl implements FoodEntityService {

    FoodEntityDaoImple foodEntityDaoImple = new FoodEntityDaoImple();



    public Map<String, String> updateFoodEntityStatus(Integer foodId, Integer statusId) {
        Map<String, String> resultMap = new LinkedHashMap<String, String>();
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setFoodID(foodId);
        foodEntity.setStatusID(statusId);
        Integer result = foodEntityDaoImple.updateEntityStatus(foodEntity);
        if (result > 0) {
            resultMap.put("successMessage", "update success");
        } else {
            resultMap.put("failMessage", "update fail");
        }
        return resultMap;
    }

    public Map<String, String> updateFoodEntity(Integer foodId, Double price) {
        Map<String, String> resultMap = new LinkedHashMap<String, String>();
        Integer result = foodEntityDaoImple.updateFoodEntityPrice(foodId, price);
        if (result > 0) {
            resultMap.put("successMessage", "update success");
        } else {
            resultMap.put("failMessage", "update fail");
        }
        return resultMap;
    }


    public Map<String, String> deleteFoodEntity(Integer foodId, Integer userId) {
        Map<String, String> resultMap = new LinkedHashMap<String, String>();
        Integer result = foodEntityDaoImple.deleteEntityByFoodId(foodId, userId);
        if (result > 0) {
            resultMap.put("SuccessMessage", "delete success");
        } else {
            resultMap.put("ErrorMessage", "fail to delete");
        }
        return resultMap;
    }

    public Map<String, String> addFoodEntity(FoodEntity foodEntity) {
        Map<String, String> resultMap = new LinkedHashMap<String, String>();
        Integer result = foodEntityDaoImple.addEntity(foodEntity);
        if (result > 0) {
            String resultMessage = "insert success";
            resultMap.put("succesMessage", resultMessage);
        } else {
            String resultMessage = "insert fail";
            resultMap.put("failMessage", resultMessage);
        }
        return resultMap;
    }

    public List<FoodEntity> findAll() {
        List<FoodEntity> foodEntityList = Collections.emptyList();
        foodEntityList = foodEntityDaoImple.findAll();
        return foodEntityList;
    }

    public List<FoodEntity> groupByTypeId(Integer typeId) {
        List<FoodEntity> foodEntityList = Collections.emptyList();
        foodEntityList = foodEntityDaoImple.groupByTypeId(Integer.valueOf(typeId));
        return foodEntityList;
    }
}
