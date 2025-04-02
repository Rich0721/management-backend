package com.system.product.management_backend.models.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductImageDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertProductImage(String code, String imageEncode) {
        String sql = "INSERT INTO first_db.product_image (code, image_encoded) VALUES (?, ?)";
        jdbcTemplate.update(sql, code, imageEncode);
    }

    public void deleteProductImage(String code) {
        String sql = "DELETE FROM first_db.product_image WHERE code = ?";
        jdbcTemplate.update(sql, code);
    }
    
}
