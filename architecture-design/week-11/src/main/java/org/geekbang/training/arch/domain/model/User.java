package org.geekbang.training.arch.domain.model;
/**
 * 领域模型：用户 User
 * ***/
public interface User {
    boolean checkPassword(String userId,String password);
    boolean checkPassword(String userId,String password,String encryptedPassword);
}
