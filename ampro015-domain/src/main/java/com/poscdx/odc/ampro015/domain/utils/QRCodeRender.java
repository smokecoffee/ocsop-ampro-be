package com.poscdx.odc.ampro015.domain.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//import com.posco.reuse.common.logging.PosLogWriterIF;
//import com.posco.reuse.common.logging.PosLogger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.EnumMap;
import java.util.Map;

public class QRCodeRender {
    String qrcodeDomain = "http://localhost:3000/asset?token=";

    private  BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }
    public String generateEmbeddedQRCodenBase64(String token) {
        int size = 250;
        int sizeLogo = 50;
        String fileType = "png";
        String imageString = null;
        String urlQRCode = this.qrcodeDomain + token;
        try {
            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(urlQRCode, BarcodeFormat.QR_CODE, size, size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            // Load the image from Resource and resize
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("logo.png").getFile());
            BufferedImage logoImage = this.resize(ImageIO.read(file), sizeLogo, sizeLogo);

            // Calculate the delta height and width between QR code and logo
            int deltaHeight = image.getHeight() - logoImage.getHeight();
            int deltaWidth = image.getWidth() - logoImage.getWidth();
            // Initialize combined image
            BufferedImage combined = new BufferedImage(image.getHeight(), image.getWidth(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = (Graphics2D) combined.getGraphics();

            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);

            // Fill color all
            graphics.setColor(new Color(0, 130,207));
            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            // Fill color eye
            graphics.setColor(new Color(0, 87,138));
            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if(i < 65 & j < 65){
                        if (byteMatrix.get(i, j)) {
                            graphics.fillRect(i, j, 1, 1);
                        }
                    }
                    if(i < 65 & j > 183){
                        if (byteMatrix.get(i, j)) {
                            graphics.fillRect(i, j, 1, 1);
                        }
                    }
                    if(i > 183 & j < 65){
                        if (byteMatrix.get(i, j)) {
                            graphics.fillRect(i, j, 1, 1);
                        }
                    }
                }
            }

            graphics.drawImage(logoImage, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);
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

//        System.out.println("Url QrCode : " + urlQRCode);
//        PosLogger.developerLog(PosLogWriterIF.INFO, "[삭제] QrCode render -> " + urlQRCode, this);
        return "data:image/png;base64," + imageString;
    }
}
