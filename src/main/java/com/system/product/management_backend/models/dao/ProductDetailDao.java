package com.system.product.management_backend.models.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.system.product.management_backend.models.ho.ProductDetailHo;

@Repository
public class ProductDetailDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertProductDetail(String code, ProductDetailHo productDetailHo) {
        String sql = "INSERT INTO first_db.product_detail (code, price, description, content) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, code, 
            productDetailHo.getPrice(), 
            productDetailHo.getDescription(), 
            productDetailHo.getContent());
    }

    public void updateProductDetail(String code, ProductDetailHo productDetailHo) {
        String sql = "UPDATE first_db.product_detail SET price = ?, description = ?, content = ? WHERE code = ?";
        jdbcTemplate.update(sql, 
            productDetailHo.getPrice(), 
            productDetailHo.getDescription(), 
            productDetailHo.getContent(), 
            code);
    }

    public ProductDetailHo getProductDetail(String code) {
        String sql = "SELECT price, description, content FROM first_db.product_detail WHERE code = ?";
        return jdbcTemplate.query(sql, ps -> ps.setString(1, code), rs -> {
            if (rs.next()) {
                ProductDetailHo productDetailHo = new ProductDetailHo();
                productDetailHo.setPrice(rs.getInt("price"));
                productDetailHo.setDescription(rs.getString("description"));
                productDetailHo.setContent(rs.getString("content"));
                return productDetailHo;
            }
            return null;
        });
    }
}
