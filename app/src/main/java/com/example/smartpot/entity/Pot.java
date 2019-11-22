package com.example.smartpot.entity;

import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.net.URL;

public class Pot {
    private Long id;
    private int image;
    private String name;
    private String description;

    public Pot() {
    }

    public Pot(int image, String name, String description) {
        this.image = image;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
