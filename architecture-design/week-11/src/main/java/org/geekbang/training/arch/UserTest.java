package org.geekbang.training.arch;

import org.geekbang.training.arch.domain.model.DefaultUser;
import org.geekbang.training.arch.domain.model.User;

public class UserTest {
    public static void main(String[] args){
        UserEntity userEntity=new UserEntity();
        userEntity.setUserId("tomcat");
        userEntity.setEncryptedPassword(MD5Utils.generate("123456"));

        User user=new DefaultUser(userEntity);
        boolean result = user.checkPassword("tomcat","123456");
        System.out.println(("密码验证：")+(result?"成功":"失败"));
    }
}

