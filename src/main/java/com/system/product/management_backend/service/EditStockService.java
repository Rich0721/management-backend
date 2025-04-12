package com.system.product.management_backend.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.system.product.management_backend.models.dao.StockDao;
import com.system.product.management_backend.models.ho.StockHo;
import com.system.product.management_backend.utils.ProductUtils;

@Service
public class EditStockService {

    @Autowired
    private StockDao stockDao;

    /**
     * 商品庫存資訊
     * 
     * @return List<StockHo> 商品庫存資訊
     */
    public List<StockHo> getStockData() {
        return stockDao.getStockData();
    }

    /**
     * 更新商品庫存資訊
     * 
     * @param stocks 商品庫存資訊
     */
    public void inventoryStock(List<StockHo> stocks) {
        stocks.forEach(stock -> stockDao.inventoryStock(stock.getCode(), stock.getStockNumbers()));
    }

    public File downloandStockData() {
        List<StockHo> stocks = stockDao.getStockData();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("商品庫存資料");

        // 標題行
        Row headerRow = sheet.createRow(0);
        String[] headers = {"商品編號", "商品名稱", "商品類別", "庫存數量"};
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }

        // 數據行
        for (int i = 0; i < stocks.size(); i++) {
            StockHo stock = stocks.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(stock.getCode());
            row.createCell(1).setCellValue(stock.getName());
            row.createCell(2).setCellValue(stock.getCategory());
            row.createCell(3).setCellValue(stock.getStockNumbers());
        }

        // 儲存檔案
        File file = new File("productList.xlsx");
        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }

    public File downloadBarCode(List<StockHo> stocks) {
        List<byte[]> barcodes = new ArrayList<>();
        stocks.forEach(stock -> barcodes
                .add(ProductUtils.generateBarcode(stock.getCode(), stock.getName())));

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("code");

        Drawing<?> drawing = sheet.createDrawingPatriarch();
        CreationHelper helper = workbook.getCreationHelper();

        int rowIndex = 0;
        int colIndex = 0;

        for (int i = 0; i < barcodes.size(); i++) {
            byte[] barcode = barcodes.get(i);

            int pictureIdx = workbook.addPicture(barcode, Workbook.PICTURE_TYPE_PNG);
            ClientAnchor anchor = helper.createClientAnchor();
            anchor.setCol1(colIndex);
            anchor.setRow1(rowIndex);

            Picture picture = drawing.createPicture(anchor, pictureIdx);
            picture.resize();

            colIndex++;
            if (colIndex >= 4) {
                colIndex = 0;
                rowIndex++;
            }
        }

        // 儲存檔案
        File file = new File("code.xlsx");
        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }
}
