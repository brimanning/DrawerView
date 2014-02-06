package com.brimanning.drawerview.ui;

import android.graphics.Bitmap;

public class DrawerItem {
    public DrawerItem(Bitmap image, String label) {
        this.image = image;
        this.label = label;
    }

    private Bitmap image;

    public Bitmap getImage() {
        return image;
    }

    private String label;

    public String getLabel() {
        return label;
    }
}
