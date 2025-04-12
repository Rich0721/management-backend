package com.system.product.management_backend.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.Instant;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class ProductUtils {

    public static String generateProductCode() {
        long unixTime = Instant.now().toEpochMilli();

        return String.format("%013d", unixTime);
    }

    public static byte[] generateBarcode(String code, String name) {

        int barcodeWidth = 200;
        int barcodeHeight = 200;
        int textHeight = 30; // 用於顯示名稱的高度

        try {
            // 生成條碼
            QRCodeWriter barcodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix =
                    barcodeWriter.encode(code, BarcodeFormat.QR_CODE, barcodeWidth, barcodeHeight);

            // 將條碼轉換為 BufferedImage
            BufferedImage barcodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            // 創建一個新的 BufferedImage，包含條碼和名稱
            BufferedImage combinedImage = new BufferedImage(barcodeWidth,
                    barcodeHeight + textHeight, BufferedImage.TYPE_INT_RGB);

            // 繪製條碼和名稱
            Graphics2D g2d = combinedImage.createGraphics();
            g2d.setColor(Color.WHITE); // 背景顏色
            g2d.fillRect(0, 0, barcodeWidth, barcodeHeight + textHeight);

            // 繪製名稱
            g2d.setColor(Color.BLACK); // 文字顏色
            g2d.setFont(new Font("標楷體", Font.BOLD, 14));
            FontMetrics fontMetrics = g2d.getFontMetrics();
            int textWidth = fontMetrics.stringWidth(name);
            int textX = (barcodeWidth - textWidth) / 2; // 水平居中
            int textY = fontMetrics.getAscent(); // 垂直位置
            g2d.drawString(name, textX, textY);

            // 繪製條碼
            g2d.drawImage(barcodeImage, 0, textHeight, null);
            g2d.dispose();

            // 將圖片轉換為字節數組
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(combinedImage, "png", outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
