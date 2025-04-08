package com.system.product.management_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.product.management_backend.models.dao.ProductDao;
import com.system.product.management_backend.models.dao.StockDao;
import com.system.product.management_backend.models.ho.ProductHo;
import com.system.product.management_backend.utils.ProductUtils;

@Service
@SuppressWarnings("deprecation")
public class EditProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private StockDao stockDao;

    @Transactional(rollbackFor = Exception.class)
    public ProductHo updateProduct(ProductHo product) {
        if (product.getCode().isEmpty()) {
            this.saveProductCode(product);
            this.saveProductImage(product.getCode(), product.getImages());
            productDao.saveProductDetail(product);
            stockDao.initStockData(product.getCode(), product.getName());
        } else {
            productDao.updateProductDetail(product);
            productDao.deleteProductImage(product.getCode());
            this.saveProductImage(product.getCode(), product.getImages());
        }
        return product;
    }

    public ProductHo saveProductCode(ProductHo product) {
        String code = ProductUtils.generateProductCode();
        productDao.saveProductCode(code, product.getName());
        product.setCode(code);
        return product;
    }

    public void saveProductImage(String code, List<String> imageEncode) {
        if (imageEncode == null || imageEncode.isEmpty()) {
            return;
        }
        imageEncode.stream().forEach(image -> productDao.saveProductImage(code, image));
    }

    public ProductHo getProduct(String code) {
        ProductHo product = productDao.getProductByCode(code) ;
        product.setImages(productDao.getProductImage(code));
        return product;
    }

    public List<ProductHo> getAllProduct() {
        List<ProductHo> product = productDao.getAllProduct() ;
        product.forEach(p -> {
            p.setImages(productDao.getProductImage(p.getCode()));
        });
        return product;
    }
}
