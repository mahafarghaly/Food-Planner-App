package com.example.foodplanner.favmeals.presenter;

import com.example.foodplanner.model.Meal;

import io.reactivex.rxjava3.core.Flowable;

public interface FavPresenter {
    Flowable getStoredMeals();

    void removeFromFav(Meal meal);

}