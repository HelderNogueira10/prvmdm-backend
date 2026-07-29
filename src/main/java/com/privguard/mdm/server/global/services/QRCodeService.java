package com.privguard.mdm.server.global.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QRCodeService {

    public byte[] generateQRCode(String text) throws Exception {

        QRCodeWriter writer = new QRCodeWriter();

        BitMatrix matrix = writer.encode(
                text,
                BarcodeFormat.QR_CODE,
                500,
                500
        );

        BufferedImage image = new BufferedImage(
                500,
                500,
                BufferedImage.TYPE_INT_RGB
        );

        for (int x = 0; x < 500; x++) {
            for (int y = 0; y < 500; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ImageIO.write(image, "PNG", baos);

        return baos.toByteArray();
    }

}
