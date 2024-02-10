package com.example.foodplanner.homefrag.presenter;


import com.example.foodplanner.homefrag.view.HomeFragmentView;
import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.model.RandomMeal;
import com.example.foodplanner.network.NetworkCallback;

import java.util.List;

public class HomeFragPresenterImpl implements NetworkCallback, HomeFragPresenter {

private HomeFragmentView _view;
private MealRepository _repo;

    public HomeFragPresenterImpl(HomeFragmentView _view , MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }
@Override
public void getCategories(){
        _repo.getAllCategories(this);
}

    @Override
    public void getRandomMeal() {
        _repo.getRandomMeal(this);
    }

    //@Override
//public void addToFav(Product product){
//        _repo.insertProduct(product);
//}

//    @Override
//    public void onSuccessResult(List meal) {
//_view.showData(meal);
//  _view.showRandom(meal);
//
//    }
@Override
public void onSuccessResult(List meal) {
    if (meal.get(0) instanceof Categories) {
        // Handle categories data
        _view.showData(meal);
    } else if (meal.get(0) instanceof RandomMeal) {
        // Handle random meal data
        _view.showRandom(meal);
    }
}




    @Override
    public void onFailureResult(String errorMsg) {

        _view.showErrMsg(errorMsg);
    }
}
