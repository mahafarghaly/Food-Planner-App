package com.example.foodplanner.mealbycategory.view;

import com.example.foodplanner.model.Meal;

import java.util.List;

public interface MealByCategoryView {
    public void showMealByCategory(List<Meal> meals);
    public void showErrMsg(String error);
}
