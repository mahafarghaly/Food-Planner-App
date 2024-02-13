package com.example.foodplanner.favmeals.presenter;

import com.example.foodplanner.model.Meal;

public interface FavPresenter {
    void getStoredMeals();

    void removeFromFav(Meal meal);

}