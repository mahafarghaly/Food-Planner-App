package com.example.foodplanner.detail.presenter;

import com.example.foodplanner.detail.view.DetailByIdView;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.model.MealResponse;


import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class DetailActivityPresenterImpl implements DetailActivityPresenter {
    private DetailByIdView _view;
    private MealRepository _repo;

    public DetailActivityPresenterImpl(DetailByIdView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getMealById(String strMeal) {

        _repo.getMealById(strMeal)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        _view.showMealById(mealResponse.getMeal());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }

//    @Override
//    public void onSuccessResult(List meals) {
//        _view.showMealById(meals);
//    }
//
//    @Override
//    public void onFailureResult(String errorMsg) {
//        _view.showErrMsg(errorMsg);
//    }
}
