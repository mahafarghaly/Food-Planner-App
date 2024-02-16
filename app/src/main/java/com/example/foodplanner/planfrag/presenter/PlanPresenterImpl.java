package com.example.foodplanner.planfrag.presenter;

import com.example.foodplanner.favmeals.view.FavMealView;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealPlan;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.planfrag.view.PlanView;

import io.reactivex.rxjava3.core.Flowable;

public class PlanPresenterImpl implements PlanPresenter{
    private PlanView _view;
    private MealRepository _repo;

    public PlanPresenterImpl(PlanView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public Flowable getStoredMealPlan() {
       return _repo.getStoredPlan();
    }

    @Override
    public void removeFromPlan(MealPlan mealPlan) {

        _repo.deletePlan(mealPlan);
    }
}
