package com.zoro.love.qr_code.factory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;


/**
 * @program: Java-Package
 * @description: Factory class that generates QR code!
 * @author: Zoro Li
 * @create: 2019-10-23 18:00
 */
public class QRCodeFactory {

    private static final int WIDTH = 350;
    private static final int HEIGHT = 350;
    private static final String QR_CODE_STORAGE_PATH = System.getProperty("user.dir") + "/qr-code" + "/qr.png";
     private static QRCodeWriter factory = new QRCodeWriter();

    public static void generateQRCodeImage(String content) {

        try {
            BitMatrix bitMatrix = factory.encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
            Path path = FileSystems.getDefault().getPath(QR_CODE_STORAGE_PATH);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

    }


}