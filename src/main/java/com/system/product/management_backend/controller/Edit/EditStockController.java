package com.system.product.management_backend.controller.Edit;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(ApiPath.GET_STOCK_PRODUCT)
    public ResponseEntity<List<StockHo>> getStock() {
        List<StockHo> stockHos = editStockService.getStockData();
        return ResponseEntity.ok(stockHos);
    }

    @PostMapping(ApiPath.UPDATE_STOCK_PRODUCT)
    public ResponseEntity<List<StockHo>> updateStock(@RequestBody List<StockHo> stockHos) {
        editStockService.inventoryStock(stockHos);
        return ResponseEntity.ok(stockHos);
    }

    @GetMapping(ApiPath.DOWNLOAD_EXCEL)
    public ResponseEntity<?> downloadExcel() {
        try {
            // 生成條碼文件
            File stockDataFile = editStockService.downloandStockData();

            // 將文件作為響應返回
            InputStreamResource resource =
                    new InputStreamResource(new FileInputStream(stockDataFile));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=" + stockDataFile.getName())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(stockDataFile.length()).body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("生成檔案失敗");
        }
    }

    @PostMapping(ApiPath.DOWNLOAD_QR_CODE)
    public ResponseEntity<?> downloadQRCode(@RequestBody List<StockHo> stockHos) {
        try {
            // 生成條碼文件
            File qrCodeFile = editStockService.downloadBarCode(stockHos);

            // 將文件作為響應返回
            InputStreamResource resource = new InputStreamResource(new FileInputStream(qrCodeFile));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=" + qrCodeFile.getName())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(qrCodeFile.length()).body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("生成條碼失敗");
        }
    }
}
