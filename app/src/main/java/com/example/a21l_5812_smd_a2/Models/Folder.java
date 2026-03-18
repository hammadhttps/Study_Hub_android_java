package com.example.a21l_5812_smd_a2.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Folder implements Serializable {

    private String name;
    private int imageCount;
    private int iconResId;
    private ArrayList<NoteImage> images;

    public Folder(String name, int imageCount, int iconResId) {
        this.name = name;
        this.imageCount = imageCount;
        this.iconResId = iconResId;
        this.images = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public ArrayList<NoteImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<NoteImage> images) {
        this.images = images;
    }



}
