package com.example.foodplanner.homefrag.presenter;


import com.example.foodplanner.homefrag.view.CategoriesView;
import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.network.NetworkCallback;

import java.util.List;
import java.util.Locale;

public class CategoriesPresenterImpl implements NetworkCallback,CategoriesPresenter {

private CategoriesView _view;
private MealRepository _repo;

    public CategoriesPresenterImpl(CategoriesView _view , MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }
@Override
public void getCategories(){
        _repo.getAllCategories(this);
}

//@Override
//public void addToFav(Product product){
//        _repo.insertProduct(product);
//}
    @Override
    public void onSuccessResult(List<Categories> categories) {
_view.showData(categories);

    }

    @Override
    public void onFailureResult(String errorMsg) {

        _view.showErrMsg(errorMsg);
    }
}
