package com.example.foodplanner.homefrag.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.db.MealLocalDataSource;
import com.example.foodplanner.homefrag.presenter.HomeFragPresenter;
import com.example.foodplanner.homefrag.presenter.HomeFragPresenterImpl;
import com.example.foodplanner.homefrag.view.adapter.CategoriesAdapter;
import com.example.foodplanner.homefrag.view.adapter.CountryAdapter;
import com.example.foodplanner.homefrag.view.adapter.MealAdapter;
import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.network.MealRemoteDataSource;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class HomeFragment extends Fragment implements HomeFragmentView , OnMealClickListener{

    public HomeFragment() {
        // Required empty public constructor
    }

    RecyclerView categoriesRecyclerView,randomRecyclerView,countryRecyclerView;
    CategoriesAdapter categoriesAdapter;
    MealAdapter mealAdapter;
CountryAdapter countryAdapter;
    HomeFragPresenter homeFragPresenter;
    LinearLayoutManager linearLayoutManager,linearLayoutManager2,linearLayoutManager3;
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
        countryRecyclerView= view.findViewById(R.id.rv_countries);


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
                MealRepository.getInstance(MealRemoteDataSource.getInstance(),
                        MealLocalDataSource.getInstance(getContext()
                )));
        categoriesRecyclerView.setLayoutManager(linearLayoutManager);
        categoriesRecyclerView.setAdapter(categoriesAdapter);
        homeFragPresenter.getCategories();
        Log.i(TAG, "onViewCreated111111111: ");
        /**Random  Meal**********************************/
        linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);// Use getContext() to get the context of the fragment
        mealAdapter = new MealAdapter(getContext(), new ArrayList<>(),this);
        homeFragPresenter = new HomeFragPresenterImpl(this,
                MealRepository.getInstance(MealRemoteDataSource.getInstance(),
                      MealLocalDataSource.getInstance(getContext())
                ));
        randomRecyclerView.setLayoutManager(linearLayoutManager2);
        randomRecyclerView.setAdapter(mealAdapter);
        homeFragPresenter.getRandomMeal();
        Log.i(TAG, "onViewCreated2222222222: ");
        /*Countries *********************************/
        linearLayoutManager3= new LinearLayoutManager(getContext());
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);// Use getContext() to get the context of the fragment
        countryAdapter = new CountryAdapter(getContext(), new ArrayList<>());
        homeFragPresenter = new HomeFragPresenterImpl(this,
                MealRepository.getInstance(MealRemoteDataSource.getInstance(),
                        MealLocalDataSource.getInstance(getContext())
                ));
        countryRecyclerView.setLayoutManager(linearLayoutManager3);
        countryRecyclerView.setAdapter(countryAdapter);
        homeFragPresenter.getCountries();
        Log.i(TAG, "onViewCreated33333333333: ");


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
    public void addMeal(Meal meal) {

        homeFragPresenter.addToFav(meal);
    }

    @Override
    public void showRandom(List<Meal> meals) {
        mealAdapter.setRandomMealList(meals);
        mealAdapter.notifyDataSetChanged();
        Log.i(TAG, "showRandomData: ");
    }

    @Override
    public void showCountry(List<Meal> country) {
countryAdapter.setCountryList(country);
countryAdapter.notifyDataSetChanged();
Log.i(TAG, "showCountries: ");

    }

    @Override
    public void onMealClick(Meal meal) {
addMeal(meal);
Toast.makeText(getContext(),"Added to favorite",Toast.LENGTH_SHORT).show();

    }


}
