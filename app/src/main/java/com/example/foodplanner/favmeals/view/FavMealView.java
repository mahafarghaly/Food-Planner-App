package com.example.foodplanner.favmeals.view;

import com.example.foodplanner.model.Meal;

import java.util.List;

public interface FavMealView {
    public void showData(/*List<Meal> meals*/);
    public void showErrMsg(String error);
    public void removeMeal(Meal meal);
}
