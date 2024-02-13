package com.example.foodplanner.mealbycategory.presenter;

import com.example.foodplanner.homefrag.view.HomeFragmentView;
import com.example.foodplanner.mealbycategory.view.MealByCategoryView;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.network.NetworkCallback;

import java.util.List;

public class MealByCategoryPresenterImpl implements NetworkCallback, MealByCategoryPresenter{
    private MealByCategoryView _view;
    private MealRepository _repo;

    public MealByCategoryPresenterImpl(MealByCategoryView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }



    @Override
    public void onSuccessResult(List meal) {
        _view.showMealByCategory(meal);

    }

    @Override
    public void onFailureResult(String errorMsg) {
        _view.showErrMsg(errorMsg);
    }

    @Override
    public void getMealByCategory(String category) {
        _repo.getMealByCategory(this,category);
    }

    @Override
    public void addToFav(Meal meal) {
        _repo.insertMeal(meal);
    }


}
