package com.system.product.management_backend.controller.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.system.product.management_backend.models.contrains.ApiPath;
import com.system.product.management_backend.models.ho.UserInfoHo;
import com.system.product.management_backend.service.UserService;

/**
 * EditProductController
 * 
 * @author Rich Wu
 * @date 2025/04/19
 */
@RestController
@RequestMapping(ApiPath.USER)
public class UserLoginController {

    @Autowired
    private UserService userService;

    /**
     * 使用者登入
     * 
     * @param username 帳號
     * @param password 密碼
     * @return 使用者資訊
     */
    @PostMapping(ApiPath.LOGIN)
    public ResponseEntity<UserInfoHo> login(@RequestBody UserInfoHo userInfo) {
        UserInfoHo user = userService.login(userInfo.getUsername(), userInfo.getPassword());
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
