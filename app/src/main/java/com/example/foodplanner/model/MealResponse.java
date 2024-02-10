package com.example.foodplanner.model;

import java.util.List;

public class MealResponse {
    List<Categories>  categories;
    List<RandomMeal>   meals;
    public List<Categories> getCategories() {
        return categories;
    }
    public List<RandomMeal> getRandomMeal() {
        return meals;
    }
}
