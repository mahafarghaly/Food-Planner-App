package com.example.foodplanner.detail.presenter;

import com.example.foodplanner.detail.view.DetailByIdView;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.network.NetworkCallback;

import java.util.List;

public class DetailActivityPresenterImpl implements NetworkCallback, DetailActivityPresenter {
    private DetailByIdView _view;
    private MealRepository _repo;

    public DetailActivityPresenterImpl(DetailByIdView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getMealById(String strMeal) {
        _repo.getMealById(this,strMeal);
    }

    @Override
    public void onSuccessResult(List meals) {
        _view.showMealById(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        _view.showErrMsg(errorMsg);
    }
}
