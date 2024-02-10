package com.example.foodplanner.homefrag.view;

import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.RandomMeal;

import java.util.List;

public interface HomeFragmentView {
    public void showData(List<Categories> categories);
    public void showErrMsg(String error);
    public void addProduct(Categories product);
    public void showRandom(List<RandomMeal>randomMeals);
}
