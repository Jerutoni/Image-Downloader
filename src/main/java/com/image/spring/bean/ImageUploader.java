package com.image.spring.bean;

import com.image.model.Image;
import org.apache.log4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class ImageUploader {
    private static final Logger log = Logger.getLogger(ImageUploader.class);

    public boolean uploadImage(Image image) {
        String nameOfImage;


        if (image.getName() == null) {
            nameOfImage = getImageName(image.getUrl());
        } else nameOfImage = image.getName();


        String format = getImageFormat(image.getUrl());
        try (InputStream in = new URL(image.getUrl()).openStream()) {
            Files.copy(in, Paths.get("images\\" + nameOfImage + format));

        } catch (IOException e) {
            log.error("Upload image error " + e);
        }
        return true;
    }


    public ResponseEntity getResponseImage(String url) {
        ResponseEntity responseEntity = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            BufferedImage picture = ImageIO.read(new URL(url));
            picture = picture.getSubimage(0, 0, 300, 300);
            ImageIO.write(picture, "png", byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            InputStream is = new ByteArrayInputStream(bytes);
            responseEntity = ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(new InputStreamResource(is));

        } catch (IOException e) {
            log.error("Image Response error " + e);
        }
        return responseEntity;
    }

    private String getImageFormat(String url) {
        int index = url.lastIndexOf('.');
        return url.substring(index);
    }

    private String getImageName(String url) {
        int index = url.lastIndexOf('/');
        return url.substring(index + 1, url.lastIndexOf('.'));
    }
}
