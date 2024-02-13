package com.example.foodplanner.mealbycategory.presenter;

import com.example.foodplanner.model.Meal;

public interface MealByCategoryPresenter {
    void getMealByCategory(String category);
    void addToFav(Meal meal);

}
