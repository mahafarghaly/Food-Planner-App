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
import com.example.foodplanner.homefrag.presenter.CategoriesPresenter;
import com.example.foodplanner.homefrag.presenter.CategoriesPresenterImpl;
import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.network.MealRemoteDataSource;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements CategoriesView {

    public HomeFragment() {
        // Required empty public constructor
    }

    RecyclerView allRecyclerView;
    CategoriesAdapter categoriesAdapter;

    CategoriesPresenter categoriesPresenter;
    LinearLayoutManager linearLayoutManager;
 String TAG ="Categories";
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
        allRecyclerView = view.findViewById(R.id.rv_categories); // Find RecyclerView here
        return view;
      
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);// Use getContext() to get the context of the fragment
        categoriesAdapter = new CategoriesAdapter(getContext(), new ArrayList<>());
        categoriesPresenter = new CategoriesPresenterImpl(this,
                MealRepository.getInstance(MealRemoteDataSource.getInstance()
                        // ProductLocalDataSource.getInstance(this)
                ));
        allRecyclerView.setLayoutManager(linearLayoutManager);
        allRecyclerView.setAdapter(categoriesAdapter);
        categoriesPresenter.getCategories();
        Log.i(TAG, "onViewCreated111111111: ");
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
        // Implement this method to add a product
    }
}
