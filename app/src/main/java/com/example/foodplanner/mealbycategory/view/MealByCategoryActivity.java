package com.example.foodplanner.mealbycategory.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.db.MealLocalDataSource;
import com.example.foodplanner.homefrag.presenter.HomeFragPresenterImpl;
import com.example.foodplanner.homefrag.view.adapter.CategoriesAdapter;
import com.example.foodplanner.mealbycategory.presenter.MealByCategoryPresenter;
import com.example.foodplanner.mealbycategory.presenter.MealByCategoryPresenterImpl;
import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.network.MealRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class MealByCategoryActivity extends AppCompatActivity implements  MealByCategoryView,OnMealByCatClickListener{
    TextView tv_category;//,tv_country,tv_instructions,tv_meal_name;//
    ImageView categoryImage;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    MealByCategoryAdapter mealByCategoryAdapter;
    MealByCategoryPresenter mealByCategoryPresenter;
    private static final String TAG = "MealByCategoryActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_by_category);
        Intent intent = getIntent();
        Categories category = (Categories) intent.getSerializableExtra("category");
        tv_category = findViewById(R.id.tv_category_name);
        categoryImage=findViewById(R.id.iv_category);
          recyclerView=findViewById(R.id.rv_mea_cat);
        //******************************************//
        tv_category.setText(category.getStrCategory());
        Glide.with(this).load(category.getStrCategoryThumb()).placeholder(R.drawable.ic_launcher_foreground).into(categoryImage);
        tv_category.setText(category.getStrCategory());
///****************************************//
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);// Use getContext() to get the context of the fragment
        mealByCategoryAdapter = new MealByCategoryAdapter(this, new ArrayList<>(),this);
        mealByCategoryPresenter = new MealByCategoryPresenterImpl(this,
                MealRepository.getInstance(MealRemoteDataSource.getInstance(),
                       MealLocalDataSource.getInstance(this)
                ));

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(mealByCategoryAdapter);
        //***============================//
        mealByCategoryPresenter.getMealByCategory(category.getStrCategory());
      //**********======================//
    }

    @Override
    public void showMealByCategory(List<Meal> meals) {
        mealByCategoryAdapter.setRandomMealList(meals);
        mealByCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        Log.i(TAG, "showErrMsg: ");
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    @Override
    public void addMeal(Meal meal) {
mealByCategoryPresenter.addToFav(meal);
    }

    @Override
    public void onMealClick(Meal meal) {
addMeal(meal);
  Toast.makeText(this,"Added to favorite",Toast.LENGTH_SHORT).show();

    }
}