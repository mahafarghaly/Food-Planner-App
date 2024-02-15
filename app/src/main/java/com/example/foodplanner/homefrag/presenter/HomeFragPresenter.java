package com.example.foodplanner.homefrag.presenter;

import com.example.foodplanner.model.Meal;

public interface HomeFragPresenter {
    void getCategories();
    void getRandomMeal();
 void getCountries();
   void addToFav(Meal meal);
}
