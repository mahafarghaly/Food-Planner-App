package com.example.foodplanner.favmeals.presenter;

import com.example.foodplanner.favmeals.view.FavMealView;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;

public class FavPresenterImpl implements FavPresenter{
    private FavMealView _view;
    private MealRepository _repo;

    public FavPresenterImpl(FavMealView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getStoredMeals() {
        _repo.getStoredData();
    }

    @Override
    public void removeFromFav(Meal meal) {
        _repo.deleteMeal(meal);
    }
}
