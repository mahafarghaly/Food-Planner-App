package com.example.foodplanner.homefrag.view;

import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.Meal;

import java.util.List;

public interface HomeFragmentView {
    public void showData(List<Categories> categories);
    public void showErrMsg(String error);
    public void addMeal(Meal meal);
    public void showRandom(List<Meal> meals);

}
