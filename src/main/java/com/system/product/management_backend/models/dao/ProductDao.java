package com.system.product.management_backend.models.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.system.product.management_backend.models.bo.ProductHo;


/**
 * @author Rich Wu
 * @deprecated This class is connected Database and CRUD product information.
 */
@Repository
public class ProductDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Get all product information from database.
     * @return List<ProductHo> product list
     */
    public List<ProductHo> getAllProduct(){
        String sql = "SELECT c.code, c.name, d.price, d.cost, d.category, d.description, d.content\r\n" + //
                        "FROM first_db.product_code as c\r\n" + //
                        "left JOIN first_db.product_detail as d\r\n" + //
                        "on c.code = d.code";
        List<ProductHo> products = new ArrayList<>();
        return jdbcTemplate.query(sql, ps -> {}, rs -> {
            while (rs.next()) {
                ProductHo product = new ProductHo();
                product.setCode(rs.getString("code"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setCost(rs.getInt("cost"));
                product.setCategory(rs.getString("category"));
                product.setDescription(rs.getString("description"));
                product.setContent(rs.getString("content"));
                products.add(product);
            }
            return products;
        });
    }

    /**
     * Get product information by code.
     * @param code product code
     * @return ProductHo product information
     */
    public ProductHo getProductByCode(String code) {
        String sql = "SELECT c.code, c.name, d.price, d.cost, d.category, d.description, d.content\r\n" + //
                        "FROM first_db.product_code as c\r\n" + //
                        "left JOIN first_db.product_detail as d\r\n" + //
                        "on c.code = d.code\r\n" + //
                        "WHERE c.code = ?";
        return jdbcTemplate.query(sql, ps -> ps.setString(1, code), rs -> {
            if (rs.next()) {
                ProductHo product = new ProductHo();
                product.setCode(rs.getString("code"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setCost(rs.getInt("cost"));
                product.setCategory(rs.getString("category"));
                product.setDescription(rs.getString("description"));
                product.setContent(rs.getString("content"));
                return product;
            }
            return null;
        });
    }

    /**
     * Get product information by code.
     * @param code product code
     * @return ProductHo product information
     */
    public void saveProductCode(String code, String name) {
        String sql = "INSERT INTO first_db.product_code (code, name) VALUES (?, ?)";
        jdbcTemplate.update(sql, code, name);
    }

    /**
     * Get product name by code.
     * @param code product code
     * @return String product name
     */
    public String getProductNameByCode(String code) {
        String sql = "SELECT name FROM first_db.product_code WHERE code = ?";
        return jdbcTemplate.query(sql, ps -> ps.setString(1, code), rs -> {
            if (rs.next()) {
                return rs.getString("name");
            }
            return "";
        });
    }

    /**
     * Get product name by code.
     * @param code product code
     * @return String product name
     */
    public void saveProductDetail(ProductHo productHo) {
        String sql = "INSERT INTO first_db.product_detail (code, price, cost, category, description, content) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, productHo.getCode(), 
            productHo.getPrice(), 
            productHo.getCost(),
            productHo.getCategory(),
            productHo.getDescription(), 
            productHo.getContent());
    }

    /**
     * Update product information by code.
     * @param productHo product information
     */
    public void updateProductDetail(ProductHo productHo) {
        String sql = "UPDATE first_db.product_detail SET price = ?, cost = ?, category=?, description = ?, content = ? WHERE code = ?";
        jdbcTemplate.update(sql, 
            productHo.getPrice(), 
            productHo.getCost(),
            productHo.getCategory(),
            productHo.getDescription(), 
            productHo.getContent(), 
            productHo.getCode());
    }

    /**
     * Save product image to database.
     * @param code
     * @param imageEncode
     */
    public void saveProductImage(String code, String imageEncode) {
        String sql = "INSERT INTO first_db.product_image (code, image_encoded) VALUES (?, ?)";
        jdbcTemplate.update(sql, code, imageEncode);
    }

    /**
     * Update product image by code.
     * @param code product code
     * @param imageEncode product image
     */
    public void deleteProductImage(String code) {
        String sql = "DELETE FROM first_db.product_image WHERE code = ?";
        jdbcTemplate.update(sql, code);
    }

    /**
     * Get product image by code.
     * @param code product code
     * @return List<String> product image list
     */
    public List<String> getProductImage(String code) {
        String sql = "SELECT image_encoded FROM first_db.product_image WHERE code = ?";
        return jdbcTemplate.query(sql, ps -> ps.setString(1, code), rs -> {
            List<String> images = new ArrayList<>();
            while (rs.next()) {
                images.add(rs.getString("image_encoded"));
            }
            return images;
        });
    }
}
