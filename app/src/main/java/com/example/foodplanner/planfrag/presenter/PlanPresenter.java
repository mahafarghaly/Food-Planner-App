package com.example.foodplanner.planfrag.presenter;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealPlan;

import io.reactivex.rxjava3.core.Flowable;

public interface PlanPresenter {
    Flowable getStoredMealPlan();

    void removeFromPlan(MealPlan mealPlan);
}
