package com.itrex.dollarprice;

import android.widget.ImageView;
import android.widget.TextView;

public class CalcModel {
    String keyElement;
    String valueElement;
    float amount;
    int image;
    TextView selectedName;
    ImageView selectedImage;

    public CalcModel(String keyElement, String valueElement, float amount, int image, TextView selectedName, ImageView selectedImage) {
        this.keyElement = keyElement;
        this.valueElement = valueElement;
        this.amount = amount;
        this.image = image;
        this.selectedName = selectedName;
        this.selectedImage = selectedImage;
    }

    public String getKeyElement() {
        return keyElement;
    }

    public void setKeyElement(String keyElement) {
        this.keyElement = keyElement;
    }

    public String getValueElement() {
        return valueElement;
    }

    public void setValueElement(String valueElement) {
        this.valueElement = valueElement;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getImage() {
        return image;
    }

    public TextView getSelectedName() {
        return selectedName;
    }

    public void setSelectedName(TextView selectedName) {
        this.selectedName = selectedName;
    }

    public ImageView getSelectedImage() {
        return selectedImage;
    }

    public void setSelectedImage(ImageView selectedImage) {
        this.selectedImage = selectedImage;
    }
}
