package com.system.product.management_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.product.management_backend.models.bo.ProductHo;
import com.system.product.management_backend.service.EditProductService;

@RestController
@RequestMapping("/edit")
public class EditProductController {

    @Autowired
    private EditProductService editProductService;
    
    @GetMapping("/getProduct/{code}")
    public ResponseEntity<ProductHo> getProduct(@PathVariable String code) {
        return ResponseEntity.ok(editProductService.getProduct(code));
    }

    @PostMapping("/updateProduct")
    public ResponseEntity<ProductHo> updateProduct(@RequestBody ProductHo product) {
        return ResponseEntity.ok(editProductService.updateProduct(product));
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<ProductHo[]> getAllProduct() {
        return ResponseEntity.ok(editProductService.getAllProduct().toArray(ProductHo[]::new));
    }
}
