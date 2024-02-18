package com.example.foodplanner.mealbyingre.presenter;

import android.util.Log;

import com.example.foodplanner.mealbycountry.view.MealByCountryView;
import com.example.foodplanner.mealbyingre.view.MealByIngreView;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.model.MealResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealByIngrePresenterImpl implements  MealByIngrePresenter{
    private MealByIngreView _view;
    private MealRepository _repo;
    private static final String TAG = "MealByIngrePresenterImp";

    public MealByIngrePresenterImpl(MealByIngreView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getMealByIngredient(String ingredient) {
        _repo.getMealByIngredient(ingredient)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        _view.showMealByIngredient(mealResponse.getMeal());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void addToFav(Meal meal) {
        _repo.insertMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{
                            Log.i(TAG, "addToFav: onSuccess insert");
                        },
                        error->{
                            Log.i(TAG, "addToFav: on error insert");
                        }
                );
    }
}
