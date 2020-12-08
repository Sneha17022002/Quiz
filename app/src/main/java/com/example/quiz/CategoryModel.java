package com.example.quiz;

public class CategoryModel {

   private String imageUrl, title;

    public CategoryModel(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title=title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String imageUrl) {
        this.title = title;
    }
}
