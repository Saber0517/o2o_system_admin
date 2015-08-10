package com.oocl.jyhon.serviceimpl;


import com.oocl.jyhon.dao.EntityDao;
import com.oocl.jyhon.daoimple.UserEntityDaoImple;
import com.oocl.jyhon.entiy.UserEntity;
import com.oocl.jyhon.service.UserEntityService;

/**
 * Created by ZHANGJA4 on 8/8/2015.
 */
public class UserEntityServiceImpl implements UserEntityService {
    UserEntityDaoImple userEntityDaoImple = new UserEntityDaoImple();

    public int addEntity(UserEntity userEntity) {
        userEntity.setRole("seller");
        userEntity.setStatusId(2);
        return userEntityDaoImple.addEntity(userEntity);
    }

    public int updateEntity(UserEntity userEntity) {
        return userEntityDaoImple.updateEntity(userEntity);
    }

    public int changeEntityStatus(UserEntity userEntity) {
        return userEntityDaoImple.updateEntityStatus(userEntity);
    }

    public int changeUserEntityStatus(UserEntity userEntity) {
        return userEntityDaoImple.updateEntityStatus(userEntity);
    }

    public UserEntity verify(UserEntity userEntity) {

        return (UserEntity) userEntityDaoImple.verify(userEntity);
    }
}
