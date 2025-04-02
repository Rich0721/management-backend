package com.system.product.management_backend.models.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.system.product.management_backend.models.ho.ProductCodeHo;

@Repository
public class ProductCodeDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertProductCode(ProductCodeHo productCodeHo) {
        String sql = "INSERT INTO first_db.product_codes (code, name) VALUES (?, ?)";
        jdbcTemplate.update(sql, productCodeHo.getCode(), productCodeHo.getName());
    }
}
