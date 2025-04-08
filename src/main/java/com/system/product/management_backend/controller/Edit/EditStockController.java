package com.system.product.management_backend.controller.Edit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.product.management_backend.models.contrains.ApiPath;
import com.system.product.management_backend.models.ho.StockHo;
import com.system.product.management_backend.service.EditStockService;

@RestController
@RequestMapping(ApiPath.EDIT)
public class EditStockController {
    
    @Autowired
    private EditStockService editStockService;

    
    @PostMapping(ApiPath.UPDATE_PRODUCT)
    public ResponseEntity<List<StockHo>> updateStockProduct(@RequestBody List<StockHo> stockHos){
        
    }
}
