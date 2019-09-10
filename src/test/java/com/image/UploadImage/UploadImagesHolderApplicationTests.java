package com.image.UploadImage;

import com.image.model.Image;
import com.image.spring.bean.ImageUploader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UploadImagesHolderApplicationTests {
    @Autowired
    private ImageUploader imageUploader;

    private final Image images[] = {new Image("https://www.nastol.com.ua/pic/201206/2560x1600/nastol.com.ua-26715.jpg",
            "Cat"), new Image("https://99px.ru/sstorage/53/2017/05/tmb_199140_5252.jpg", "Japan"),
            new Image("https://dontreg.ru/wp-content/uploads/2019/04/java_logo.png", "Java")};


    @Test
    public void uploadOneImageTest() {
        Image image = images[0];
        boolean isUpload = imageUploader.uploadImage(image);
        Assert.assertTrue(isUpload);
    }

    @Test
    public void getResponseImageTest() {
        ResponseEntity responseEntity = imageUploader.getResponseImage(images[1].getUrl());
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

}
