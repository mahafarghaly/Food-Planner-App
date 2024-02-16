package com.example.foodplanner.detail.view;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealPlan;

import java.util.List;

public interface DetailByIdView {
    public void showMealById(List<Meal> meals);
    public void showErrMsg(String error);
    public void addMeal(Meal meal);
    public void addPlan(MealPlan mealPlan);
}
