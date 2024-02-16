package com.example.foodplanner.planfrag.view;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealPlan;

public interface PlanView {
    public void showData();
    public void showErrMsg(String error);
    public void removeMealPlan(MealPlan mealPlan);
}
