package com.example.foodplanner.mealbycountry.presenter;

import android.util.Log;

import com.example.foodplanner.mealbycategory.view.MealByCategoryView;
import com.example.foodplanner.mealbycountry.view.MealByCountryView;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.model.MealResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealByCountryPresenterImpl implements  MealByCountryPresenter {
    private MealByCountryView _view;
    private MealRepository _repo;
    private static final String TAG = "MealByCountryPresenterI";
    public MealByCountryPresenterImpl(MealByCountryView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getMealByCountry(String strArea) {
        _repo.getMealByArea(strArea)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        _view.showMealByCountry(mealResponse.getMeal());

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