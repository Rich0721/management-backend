package com.system.product.management_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.system.product.management_backend.models.dao.UserDao;
import com.system.product.management_backend.models.ho.UserInfoHo;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 使用者登入
     * 
     * @param username 帳號
     * @param password 密碼
     * @return 使用者資訊
     */
    public UserInfoHo login(String username, String password) {
        return userDao.login(username, password);
    }

}
