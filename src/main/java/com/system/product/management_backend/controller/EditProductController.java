package com.system.product.management_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.product.management_backend.models.bo.Product;
import com.system.product.management_backend.service.EditProductService;

@RestController
@RequestMapping("/edit")
public class EditProductController {

    @Autowired
    private EditProductService editProductService;
    
    @GetMapping("/getProduct/{code}")
    public ResponseEntity<Product> getProduct(@PathVariable String code) {
        return ResponseEntity.ok(editProductService.getProduct(code));
    }

    @PostMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(Product product) {
        return ResponseEntity.ok(editProductService.updateProduct(product));
    }
}
