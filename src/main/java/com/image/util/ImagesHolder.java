package com.image.util;

import com.image.model.Image;

import java.util.ArrayList;
import java.util.List;

public class ImagesHolder {
    private List<Image> images = new ArrayList<>();

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
