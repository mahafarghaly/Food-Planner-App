package com.example.foodplanner.homefrag.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.homefrag.presenter.HomeFragPresenter;
import com.example.foodplanner.homefrag.presenter.HomeFragPresenterImpl;
import com.example.foodplanner.homefrag.view.adapter.CategoriesAdapter;
import com.example.foodplanner.homefrag.view.adapter.RandomAdapter;
import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.model.RandomMeal;
import com.example.foodplanner.network.MealRemoteDataSource;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeFragmentView {

    public HomeFragment() {
        // Required empty public constructor
    }

    RecyclerView categoriesRecyclerView,randomRecyclerView;
    CategoriesAdapter categoriesAdapter;
    RandomAdapter randomAdapter;

    HomeFragPresenter homeFragPresenter;
    LinearLayoutManager linearLayoutManager,linearLayoutManager2;

 String TAG ="HomeFragment";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoriesRecyclerView = view.findViewById(R.id.rv_categories); // Find RecyclerView here
        randomRecyclerView =  view.findViewById(R.id.rv_random);
        return view;
      
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);// Use getContext() to get the context of the fragment
        categoriesAdapter = new CategoriesAdapter(getContext(), new ArrayList<>());
        homeFragPresenter = new HomeFragPresenterImpl(this,
                MealRepository.getInstance(MealRemoteDataSource.getInstance()
                        // ProductLocalDataSource.getInstance(this)
                ));
        categoriesRecyclerView.setLayoutManager(linearLayoutManager);
        categoriesRecyclerView.setAdapter(categoriesAdapter);
        homeFragPresenter.getCategories();
        Log.i(TAG, "onViewCreated111111111: ");
        /**Random  Meal**********************************/
        linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);// Use getContext() to get the context of the fragment
        randomAdapter = new RandomAdapter(getContext(), new ArrayList<>());
        homeFragPresenter = new HomeFragPresenterImpl(this,
                MealRepository.getInstance(MealRemoteDataSource.getInstance()
                        // ProductLocalDataSource.getInstance(this)
                ));
        randomRecyclerView.setLayoutManager(linearLayoutManager2);
        randomRecyclerView.setAdapter(randomAdapter);
        homeFragPresenter.getRandomMeal();
        Log.i(TAG, "onViewCreated2222222222: ");
    }



    @Override
    public void showData(List<Categories> categories) {
        categoriesAdapter.setCategoriesList(categories);
        categoriesAdapter.notifyDataSetChanged();
        Log.i(TAG, "showData: ");
    }

    @Override
    public void showErrMsg(String error) {
        Log.i(TAG, "showErrMsg: ");
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    @Override
    public void addProduct(Categories product) {

    }

    @Override
    public void showRandom(List<RandomMeal> randomMeals) {
        randomAdapter.setRandomMealList(randomMeals);
        randomAdapter.notifyDataSetChanged();
        Log.i(TAG, "showRandomData: ");
    }
}
