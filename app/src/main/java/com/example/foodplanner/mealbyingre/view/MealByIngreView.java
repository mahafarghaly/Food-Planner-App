package com.example.foodplanner.mealbyingre.view;

import com.example.foodplanner.model.Meal;

import java.util.List;

public interface MealByIngreView {
    public void showMealByIngredient(List<Meal> meals);
    public void showErrMsg(String error);
    public void addMeal(Meal meal);
}
