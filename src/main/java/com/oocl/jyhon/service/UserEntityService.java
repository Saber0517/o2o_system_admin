package com.oocl.jyhon.service;

import com.oocl.jyhon.entiy.FoodRelatePackageEntity;
import com.oocl.jyhon.entiy.UserEntity;

/**
 * Created by ZHANGJA4 on 8/8/2015.
 */
public interface UserEntityService {
    public int addEntity(UserEntity userEntity);

    public int changeUserEntityStatus(UserEntity userEntity);

    public UserEntity verify(UserEntity userEntity);

    public int updateEntity(UserEntity userEntity);

    public int changeEntityStatus(UserEntity userEntity);
}
