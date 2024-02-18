package com.example.foodplanner.mealbyingre.presenter;

import com.example.foodplanner.model.Meal;

public interface MealByIngrePresenter {
    void getMealByIngredient(String ingredient);
    void addToFav(Meal meal);
}
