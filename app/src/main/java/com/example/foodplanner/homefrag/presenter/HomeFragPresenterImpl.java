package com.example.foodplanner.homefrag.presenter;


import android.util.Log;

import com.example.foodplanner.homefrag.view.HomeFragmentView;
import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealResponse;


import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragPresenterImpl implements HomeFragPresenter {

private HomeFragmentView _view;
private MealRepository _repo;
    private static final String TAG = "HomeFragPresenterImpl";
    public HomeFragPresenterImpl(HomeFragmentView _view , MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }
@Override
public void getCategories(){
        _repo.getAllCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        _view.showData(mealResponse.getCategories());
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
    public void getRandomMeal() {
        _repo.getRandomMeal()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse productsResponse) {
                        _view.showRandom(productsResponse.getMeal());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        _view.showErrMsg(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getCountries() {
_repo.getAllCountries()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<MealResponse>(){

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull MealResponse mealResponse) {
            _view.showCountry(mealResponse.getMeal());
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
public void addToFav(Meal meal){
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
