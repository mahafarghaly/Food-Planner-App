package com.example.foodplanner.network;

import com.example.foodplanner.model.Categories;

import java.util.List;

public interface NetworkCallback {
    public void onSuccessResult(List<Categories> products);
    public void onFailureResult (String errorMsg);
}
