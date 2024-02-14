package com.example.foodplanner.mealbycategory.presenter;

import android.util.Log;

import com.example.foodplanner.homefrag.view.HomeFragmentView;
import com.example.foodplanner.mealbycategory.view.MealByCategoryView;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.model.MealResponse;


import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealByCategoryPresenterImpl implements  MealByCategoryPresenter{
    private MealByCategoryView _view;
    private MealRepository _repo;
    private static final String TAG = "MealByCategoryPresenter";
    public MealByCategoryPresenterImpl(MealByCategoryView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }



//    @Override
//    public void onSuccessResult(List meal) {
//        _view.showMealByCategory(meal);
//
//    }
//
//    @Override
//    public void onFailureResult(String errorMsg) {
//        _view.showErrMsg(errorMsg);
//    }

    @Override
    public void getMealByCategory(String category) {

        _repo.getMealByCategory(category)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        _view.showMealByCategory(mealResponse.getMeal());
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
