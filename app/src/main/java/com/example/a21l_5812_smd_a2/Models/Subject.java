package com.example.a21l_5812_smd_a2.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Subject implements Serializable {

    private String name;
    private  int folderCount;
    private  int iconResId;
    private ArrayList<Folder>folders;


    public Subject(String name, int folderCount, int iconResId) {
        this.name = name;
        this.folderCount = folderCount;
        this.iconResId = iconResId;
        this.folders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFolderCount() {
        return folderCount;
    }

    public void setFolderCount(int folderCount) {
        this.folderCount = folderCount;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public ArrayList<Folder> getFolders() {
        return folders;
    }

    public void setFolders(ArrayList<Folder> folders) {
        this.folders = folders;
    }



}
