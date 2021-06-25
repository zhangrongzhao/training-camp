package org.geekbang.training.arch.domain.model;

import org.geekbang.training.arch.MD5Utils;
import org.geekbang.training.arch.UserEntity;

public class DefaultUser implements User {
    private UserEntity userEntity;
    public DefaultUser(UserEntity userEntity){
        this.userEntity=userEntity;
    }


    public boolean checkPassword(String userId, String password) {
        if(userId==null||
           password==null||
           this.userEntity==null||
           !userId.equals(this.userEntity.getUserId()))
        {
            return false;
        }
        return checkPassword(userId,password,this.userEntity.getEncryptedPassword());
    }


    public boolean checkPassword(String userId, String password, String encryptedPassword) {
        return MD5Utils.verify(password, encryptedPassword);
    }
}
