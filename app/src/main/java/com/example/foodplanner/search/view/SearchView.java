package com.example.foodplanner.search.view;

import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.Meal;

import java.util.List;

public interface SearchView {
    public void showCategory(List<Categories> categories);
    public void showErrMsg(String error);

    public void showCountry(List<Meal> country);
    public void showIngredient(List<Meal> ingredient);

}
