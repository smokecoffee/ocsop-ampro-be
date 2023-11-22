package com.poscdx.odc.ampro015.domain.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.EnumMap;
import java.util.Map;

public class QRCodeRender {
    public String generateEmbeddedQRCodenBase64(String myCodeText) {

        String qrCode=null;
        int size = 250;
        String fileType = "png";
        String imageString = null;

        try {

            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
            hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                    size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            // Load the image from class path
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("logo-3.png").getFile());
            BufferedImage logoImage = ImageIO.read(file);
            // Even you can load the image from file path
//            BufferedImage logoImage = ImageIO.read(new File("C:\\sites\\python\\QRCode\\posco\\logo-3.png"));

            // Calculate the delta height and width between QR code and logo
            int deltaHeight = image.getHeight() - logoImage.getHeight();
            int deltaWidth = image.getWidth() - logoImage.getWidth();
            // Initialize combined image
            BufferedImage combined = new BufferedImage(image.getHeight(), image.getWidth(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = (Graphics2D) combined.getGraphics();

            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);

            graphics.setColor(new Color(237, 49,36));

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            graphics.drawImage(logoImage, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);

            //ImageIO.write(combined, fileType, myFile);
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();

            try {
                ImageIO.write(combined, fileType, bos);
                // Convert image to Base64 encoded String
                imageString= Base64.getEncoder().encodeToString(bos.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageString;
    }
}
