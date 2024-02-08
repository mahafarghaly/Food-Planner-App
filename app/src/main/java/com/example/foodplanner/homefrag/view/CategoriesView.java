package com.example.foodplanner.homefrag.view;

import com.example.foodplanner.model.Categories;

import java.util.List;

public interface CategoriesView {
    public void showData(List<Categories> categories);
    public void showErrMsg(String error);
    public void addProduct(Categories product);
}
