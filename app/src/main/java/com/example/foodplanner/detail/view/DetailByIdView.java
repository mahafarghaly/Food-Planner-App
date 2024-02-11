package com.example.foodplanner.detail.view;

import com.example.foodplanner.model.Meal;

import java.util.List;

public interface DetailByIdView {
    public void showMealById(List<Meal> meals);
    public void showErrMsg(String error);
}
