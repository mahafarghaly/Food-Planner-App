package com.example.foodplanner.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Random_table")
public class RandomMeal implements Serializable {
    public RandomMeal(@NonNull String idMeal, String strMeal, String strCategory, String strArea, String strInstructions, String strMealThumb, String strYoutube, String strIngredient1, String strIngredient2, String strIngredient3, String strIngredient4, String strIngredient5, String strIngredient6, String strIngredient7, String strIngredient8, String strIngredient9, String strIngredient10, String strIngredient11, String trMeasure1, String trMeasure2, String trMeasure3, String trMeasure4, String trMeasure5, String trMeasure6, String trMeasure7, String trMeasure8, String trMeasure9, String trMeasure10, String trMeasure11) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strInstructions = strInstructions;
        this.strMealThumb = strMealThumb;
        this.strYoutube = strYoutube;
        this.strIngredient1 = strIngredient1;
        this.strIngredient2 = strIngredient2;
        this.strIngredient3 = strIngredient3;
        this.strIngredient4 = strIngredient4;
        this.strIngredient5 = strIngredient5;
        this.strIngredient6 = strIngredient6;
        this.strIngredient7 = strIngredient7;
        this.strIngredient8 = strIngredient8;
        this.strIngredient9 = strIngredient9;
        this.strIngredient10 = strIngredient10;
        this.strIngredient11 = strIngredient11;
        this.trMeasure1 = trMeasure1;
        this.trMeasure2 = trMeasure2;
        this.trMeasure3 = trMeasure3;
        this.trMeasure4 = trMeasure4;
        this.trMeasure5 = trMeasure5;
        this.trMeasure6 = trMeasure6;
        this.trMeasure7 = trMeasure7;
        this.trMeasure8 = trMeasure8;
        this.trMeasure9 = trMeasure9;
        this.trMeasure10 = trMeasure10;
        this.trMeasure11 = trMeasure11;
    }

    @PrimaryKey
    @NonNull
    private String idMeal;

    private String strMeal;
    private String strCategory;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strYoutube;
    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strIngredient4;
    private String strIngredient5;
    private String strIngredient6;
    private String strIngredient7;
    private String strIngredient8;
    private String strIngredient9;
    private String strIngredient10;
    private String strIngredient11;
    private String trMeasure1;
    private String trMeasure2;
    private String trMeasure3;
    private String trMeasure4;
    private String trMeasure5;
    private String trMeasure6;
    private String trMeasure7;
    private String trMeasure8;
    private String trMeasure9;
    private String trMeasure10;
    private String trMeasure11;

    @NonNull
    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(@NonNull String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public void setStrIngredient5(String strIngredient5) {
        this.strIngredient5 = strIngredient5;
    }

    public String getStrIngredient6() {
        return strIngredient6;
    }

    public void setStrIngredient6(String strIngredient6) {
        this.strIngredient6 = strIngredient6;
    }

    public String getStrIngredient7() {
        return strIngredient7;
    }

    public void setStrIngredient7(String strIngredient7) {
        this.strIngredient7 = strIngredient7;
    }

    public String getStrIngredient8() {
        return strIngredient8;
    }

    public void setStrIngredient8(String strIngredient8) {
        this.strIngredient8 = strIngredient8;
    }

    public String getStrIngredient9() {
        return strIngredient9;
    }

    public void setStrIngredient9(String strIngredient9) {
        this.strIngredient9 = strIngredient9;
    }

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public void setStrIngredient10(String strIngredient10) {
        this.strIngredient10 = strIngredient10;
    }

    public String getStrIngredient11() {
        return strIngredient11;
    }

    public void setStrIngredient11(String strIngredient11) {
        this.strIngredient11 = strIngredient11;
    }

    public String getTrMeasure1() {
        return trMeasure1;
    }

    public void setTrMeasure1(String trMeasure1) {
        this.trMeasure1 = trMeasure1;
    }

    public String getTrMeasure2() {
        return trMeasure2;
    }

    public void setTrMeasure2(String trMeasure2) {
        this.trMeasure2 = trMeasure2;
    }

    public String getTrMeasure3() {
        return trMeasure3;
    }

    public void setTrMeasure3(String trMeasure3) {
        this.trMeasure3 = trMeasure3;
    }

    public String getTrMeasure4() {
        return trMeasure4;
    }

    public void setTrMeasure4(String trMeasure4) {
        this.trMeasure4 = trMeasure4;
    }

    public String getTrMeasure5() {
        return trMeasure5;
    }

    public void setTrMeasure5(String trMeasure5) {
        this.trMeasure5 = trMeasure5;
    }

    public String getTrMeasure6() {
        return trMeasure6;
    }

    public void setTrMeasure6(String trMeasure6) {
        this.trMeasure6 = trMeasure6;
    }

    public String getTrMeasure7() {
        return trMeasure7;
    }

    public void setTrMeasure7(String trMeasure7) {
        this.trMeasure7 = trMeasure7;
    }

    public String getTrMeasure8() {
        return trMeasure8;
    }

    public void setTrMeasure8(String trMeasure8) {
        this.trMeasure8 = trMeasure8;
    }

    public String getTrMeasure9() {
        return trMeasure9;
    }

    public void setTrMeasure9(String trMeasure9) {
        this.trMeasure9 = trMeasure9;
    }

    public String getTrMeasure10() {
        return trMeasure10;
    }

    public void setTrMeasure10(String trMeasure10) {
        this.trMeasure10 = trMeasure10;
    }

    public String getTrMeasure11() {
        return trMeasure11;
    }

    public void setTrMeasure11(String trMeasure11) {
        this.trMeasure11 = trMeasure11;
    }

    @Override
    public String toString() {
        return "RandomMeal{" +
                "idMeal='" + idMeal + '\'' +
                ", strMeal='" + strMeal + '\'' +
                ", strCategory='" + strCategory + '\'' +
                ", strArea='" + strArea + '\'' +
                ", strInstructions='" + strInstructions + '\'' +
                ", strMealThumb='" + strMealThumb + '\'' +
                ", strYoutube='" + strYoutube + '\'' +

                '}';
    }
}
