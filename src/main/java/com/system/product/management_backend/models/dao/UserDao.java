package com.system.product.management_backend.models.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.system.product.management_backend.models.ho.UserInfoHo;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 使用者登入
     * 
     * @param account 帳號
     * @param password 密碼
     * @return 使用者資訊
     */
    public UserInfoHo login(String username, String password) {
        String sql =
                "SELECT username, user_permission FROM first_db.user_info WHERE username = ? AND password = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {username, password}, (rs, rowNum) -> {
            UserInfoHo userInfo = new UserInfoHo();
            userInfo.setUsername(rs.getString("username"));
            userInfo.setUserPermission(rs.getString("user_permission"));
            return userInfo;
        });
    }
}
