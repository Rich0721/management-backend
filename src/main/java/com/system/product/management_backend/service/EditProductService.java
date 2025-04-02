package com.system.product.management_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.product.management_backend.models.bo.Product;
import com.system.product.management_backend.models.dao.ProductCodeDao;
import com.system.product.management_backend.models.dao.ProductDetailDao;
import com.system.product.management_backend.models.dao.ProductImageDao;
import com.system.product.management_backend.models.ho.ProductCodeHo;
import com.system.product.management_backend.models.ho.ProductDetailHo;
import com.system.product.management_backend.utils.ProductUtils;

@Service
public class EditProductService {

    @Autowired
    private ProductCodeDao productCodeDao;

    @Autowired
    private ProductImageDao productImageDao;

    @Autowired
    private ProductDetailDao productDetailDao;
    
    public Product updateProduct(Product product) {
        // Connect Database and update product information
        if (product.getCode().isEmpty()) {
            this.insertProductCode(product);
            this.insertProductImage(product.getCode(), product.getImages());
            this.insertProductDetail(product.getCode(), product);
        }
        return product;
    }

    public Product insertProductCode(Product product) {
        // Connect Database and insert product information
        ProductCodeHo productCodeHo = new ProductCodeHo();
        productCodeHo.setCode(ProductUtils.generateProductCode());
        productCodeHo.setName(product.getName());
        productCodeDao.insertProductCode(productCodeHo);
        product.setCode(productCodeHo.getCode());
        return product;
    }

    public void insertProductImage(String code, List<String> imageEncode) {
        if (imageEncode == null || imageEncode.isEmpty()) {
            return;
        }
        imageEncode.stream().forEach(image -> productImageDao.insertProductImage(code, image));
    }

    public void insertProductDetail(String code, Product product) {
        if (product.getDescription() == null || product.getDescription().isEmpty()) {
            return;
        }
        ProductDetailHo productDetailHo = new ProductDetailHo();
        productDetailHo.setPrice(product.getPrice());
        productDetailHo.setDescription(product.getDescription());
        productDetailHo.setContent(product.getContent());
        productDetailDao.insertProductDetail(code, productDetailHo);
    }


    public Product getProduct(String code) {
        // Mock product for demonstration purposes
        Product product = new Product();
        product.setCode(code);
        product.setContent( " <p>Content</p>");
        product.setName("乃木坂46");
        product.setDescription("Test Description");
        product.setPrice(100);
        product.setCategory("女子偶像團體");
        
        // product.setImages(List.of(test));
        return product;
    }
}
