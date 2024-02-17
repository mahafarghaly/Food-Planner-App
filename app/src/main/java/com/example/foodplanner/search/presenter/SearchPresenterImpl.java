package com.example.foodplanner.search.presenter;

import com.example.foodplanner.homefrag.view.HomeFragmentView;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.search.view.SearchView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class SearchPresenterImpl implements  SearchPresenter
{
    private SearchView _view;
    private MealRepository _repo;

    public SearchPresenterImpl(SearchView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getCategories() {
        _repo.getAllCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        _view.showCategory(mealResponse.getCategories());
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


}
