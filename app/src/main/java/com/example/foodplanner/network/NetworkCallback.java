package com.example.foodplanner.network;

import com.example.foodplanner.model.Categories;

import java.util.List;

public interface NetworkCallback <T>{
    public void onSuccessResult(List<T> products);
    public void onFailureResult (String errorMsg);

}
