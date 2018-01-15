package com.mayarafelix.mastermind.model;

import android.widget.ImageButton;

public class Item
{
    private int imageResourceId;
    private ImageButton imageButton;

    public Item(ImageButton imageButton)
    {
        this.imageButton = imageButton;
        this.imageResourceId = 0;
    }

    // Getters & Setters

    public ImageButton getImageButtonObject()
    {
        return imageButton;
    }

    public void setImageButton(ImageButton imageButton)
    {
        this.imageButton = imageButton;
    }

    public int getImageResourceId()
    {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId)
    {
        this.imageResourceId = imageResourceId;
    }
}
