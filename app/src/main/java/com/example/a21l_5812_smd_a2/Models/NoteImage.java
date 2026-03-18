package com.example.a21l_5812_smd_a2.Models;

import java.io.Serializable;

public class NoteImage implements Serializable {

    private String imagePath;
    private String name;
    private long dateAdded;

    public NoteImage(String imagePath, String name) {
        this.imagePath = imagePath;
        this.name = name;
        this.dateAdded = System.currentTimeMillis();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

}
