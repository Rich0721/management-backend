package com.system.product.management_backend.models.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.system.product.management_backend.models.ho.StockHo;

@Repository
public class StockDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 商品庫存初始化
     * 
     * @param code 商品編號
     * @param name 商品名稱
     * @return 數量
     */
    public int initStockData(String code, String name) {
        String sql = "INSERT INTO first_db.stock (code, name, numbers) VALUES (?, ?, 0)";
        return jdbcTemplate.update(sql, code, name);
    }

    public List<StockHo> getStockData() {
        String sql = "SELECT c.code, c.name, d.category, s.numbers \r\n" + //
                "FROM first_db.product_code c\r\n" + //
                "JOIN first_db.product_stock s ON c.code = s.code\r\n" + //
                "JOIN first_db.product_detail d ON c.code = d.code";

        List<StockHo> stocks = new ArrayList<>();
        return jdbcTemplate.query(sql, ps -> {
        }, rs -> {
            while (rs.next()) {
                StockHo stock = new StockHo();
                stock.setCode(rs.getString("code"));
                stock.setName(rs.getString("name"));
                stock.setStockNumbers(rs.getInt("numbers"));
                stock.setCategory(rs.getString("category"));
                stocks.add(stock);
            }
            return stocks;
        });

    }

    /**
     * 庫存盤點
     * 
     * @param code 商品編號
     * @param numbers 數量
     */
    public int inventoryStock(String code, int numbers) {
        String sql = "UPDATE first_db.product_stock SET numbers = ? WHERE code = ?";
        return jdbcTemplate.update(sql, numbers, code);
    }

    /**
     * 販賣與進貨庫存調整
     * 
     * @param code 商品編號
     * @param numbers 數量(-1表示販賣，+1表示進貨)
     */
    public int saleAndPurchaseStock(String code, int numbers) {
        String sql = "UPDATE first_db.stock SET numbers = numbers + ? WHERE code = ?";
        return jdbcTemplate.update(sql, numbers, code);
    }
}
