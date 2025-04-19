package com.system.product.management_backend.controller.Edit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.system.product.management_backend.models.contrains.ApiPath;
import com.system.product.management_backend.models.ho.ProductHo;
import com.system.product.management_backend.service.EditProductService;


/**
 * EditProductController
 * 
 * @author Rich Wu
 * @date 2025/04/08
 */
@RestController
@RequestMapping(ApiPath.EDIT)
public class EditProductController {

    @Autowired
    private EditProductService editProductService;

    @GetMapping(ApiPath.GET_PRODUCT)
    public ResponseEntity<ProductHo> getProduct(@PathVariable String code) {
        return ResponseEntity.ok(editProductService.getProduct(code));
    }

    @PostMapping(ApiPath.UPDATE_PRODUCT)
    public ResponseEntity<ProductHo> updateProduct(@RequestBody ProductHo product) {
        return ResponseEntity.ok(editProductService.updateProduct(product));
    }

    @GetMapping(ApiPath.GET_ALL_PRODUCT)
    public ResponseEntity<ProductHo[]> getAllProduct() {
        return ResponseEntity.ok(editProductService.getAllProduct().toArray(ProductHo[]::new));
    }
}
