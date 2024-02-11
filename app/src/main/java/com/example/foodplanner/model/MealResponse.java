package com.example.foodplanner.model;

import java.util.List;

public class MealResponse {
    List<Categories>  categories;
    List<Meal>   meals;
    public List<Categories> getCategories() {
        return categories;
    }
    public List<Meal> getMeal() {
        return meals;
    }


}
