package com.example.foodplanner.detail.presenter;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealPlan;

public interface DetailActivityPresenter {
    void getMealById(String strMeal);
    void addToFav(Meal meal);
    void addToPlan(MealPlan mealPlan);
}
