package com.example.foodplanner.mealbycountry.presenter;

import com.example.foodplanner.model.Meal;

public interface MealByCountryPresenter {
    void getMealByCountry(String strArea);
    void addToFav(Meal meal);

}
