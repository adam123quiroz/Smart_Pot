package com.example.smartpot.entity;

import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.net.URL;

public class Pot {
    private Long id;
    private Drawable image;
    private String name;
    private String description;

    public Pot() {
    }

    public Pot(String url, String name, String description) {
        setImage(url);
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            this.image = d;
        } catch (Exception e) {
            this.image = null;
        }
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
