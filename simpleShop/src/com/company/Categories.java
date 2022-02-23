package com.company;

public class Categories {
    public String categoryName;
    public String categoryDescription;


    //constructor for item
    public Categories(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    @Override
    public String toString() {
        return this.categoryName;
    }

    public String getItemName() {
        return categoryName;
    }

    public String getDescription() {
        return categoryDescription;
    }
}
