package com.example.foodplanner.mealbycountry.view;

import com.example.foodplanner.model.Meal;

import java.util.List;

public interface MealByCountryView {
    public void showMealByCountry(List<Meal> meals);
    public void showErrMsg(String error);
    public void addMeal(Meal meal);
}
