package com.image.controller;

import com.image.model.Image;
import com.image.util.BaseResponse;
import com.image.util.ImagesHolder;
import com.image.spring.bean.ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/image")
public class ImageController {

    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    private static final int CODE_SUCCESS = 200;
    private static final int CODE_ERROR = 500;

    @Autowired
    ImageUploader imageUploader;

    @PostMapping("/upload")
    @ResponseBody
    public BaseResponse uploadImage(@RequestBody ImagesHolder images) {
        boolean isUpload = false;
        for (Image image : images.getImages()) {

            isUpload = imageUploader.uploadImage(image);
            if (!isUpload) {
                return new BaseResponse(ERROR_STATUS, CODE_ERROR);
            }

        }
        return new BaseResponse(SUCCESS_STATUS, CODE_SUCCESS);
    }

    @PostMapping("/uploadOne/")
    @ResponseBody
    public ResponseEntity uploadImage(@RequestBody Image image) {
        boolean isUpload = false;

        isUpload = imageUploader.uploadImage(image);
        ResponseEntity responseEntity = imageUploader.getResponseImage(image.getUrl());
        if (isUpload && responseEntity != null) {
            return imageUploader.getResponseImage(image.getUrl());
        } else return ResponseEntity.badRequest().body(new BaseResponse(ERROR_STATUS, CODE_ERROR));
    }

}
