package com.example.foodplanner.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodplanner.R;
import com.example.foodplanner.db.MealLocalDataSource;
import com.example.foodplanner.homefrag.presenter.HomeFragPresenter;
import com.example.foodplanner.homefrag.presenter.HomeFragPresenterImpl;
import com.example.foodplanner.homefrag.view.adapter.CategoriesAdapter;
import com.example.foodplanner.homefrag.view.adapter.CountryAdapter;
import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.network.MealRemoteDataSource;
import com.example.foodplanner.search.presenter.SearchPresenter;
import com.example.foodplanner.search.presenter.SearchPresenterImpl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchFragment extends Fragment implements SearchView{

    private RecyclerView recyclerView;
    private CategoriesAdapter categoriesAdapter;
    private LinearLayoutManager linearLayoutManager,linearLayoutManager2;
    private EditText searchEditText;
    CountryAdapter countryAdapter;
    SearchPresenter searchPresenter;
    EditText search;
    Button btn_category,btn_country;

    public SearchFragment() {
        // Required empty public constructor
    }

    private static final String TAG = "SearchFragment";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Assume you have a method to get all categories
        // For example, allCategories = getCategoryList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.rv_search);
        search=view.findViewById(R.id.searchEditText);
        btn_category=view.findViewById(R.id.btn_categories);
        btn_country=view.findViewById(R.id.btn_country);
        btn_category .setOnClickListener(v -> {
            recyclerView.setAdapter(categoriesAdapter); // Set categories adapter
            searchPresenter.getCategories(); // Fetch and display categories data
        });
        btn_country.setOnClickListener(v -> {
            recyclerView.setAdapter(countryAdapter); // Set countries adapter
            searchPresenter.getCountries(); // Fetch and display countries data
        });

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);// Use getContext() to get the context of the fragment
        categoriesAdapter = new CategoriesAdapter(getContext(), new ArrayList<>());
        searchPresenter = new SearchPresenterImpl(this,
                MealRepository.getInstance(MealRemoteDataSource.getInstance(),
                        MealLocalDataSource.getInstance(getContext()
                        )));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
//        recyclerView.setAdapter(categoriesAdapter);
//        searchPresenter.getCategories();

        //countries
        linearLayoutManager2= new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);// Use getContext() to get the context of the fragment
        countryAdapter = new CountryAdapter(getContext(), new ArrayList<>());
        searchPresenter = new SearchPresenterImpl(this,
                MealRepository.getInstance(MealRemoteDataSource.getInstance(),
                        MealLocalDataSource.getInstance(getContext())
                ));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
//        recyclerView.setAdapter(countryAdapter);
//        searchPresenter.getCountries();

        Observable.create((ObservableOnSubscribe<String>) emitter ->
                        search.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                                emitter.onNext(charSequence.toString());

                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                            }
                        }))
                .debounce(300, TimeUnit.MILLISECONDS) // Add a debounce to avoid rapid searches
                .map(String::toLowerCase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchTerm -> {
                    List<Categories> filteredCategories = filterCategories(searchTerm);
                   List<Meal> filteredCountries = filterCountries(searchTerm);
                    categoriesAdapter.setCategoriesList(filteredCategories);
                    countryAdapter.setCountryList(filteredCountries);
                    categoriesAdapter.notifyDataSetChanged();
                    countryAdapter.notifyDataSetChanged();
                });

    }
    private List<Categories> filterCategories(String searchTerm) {
        return  Arrays.stream(categoriesAdapter.getCategoriesList().toArray(new Categories[0])).filter(item->item.getStrCategory().toLowerCase().contains(searchTerm)).collect(Collectors.toList());

    }
    private List<Meal> filterCountries(String searchTerm) {
        return  Arrays.stream(countryAdapter.getCountryList().toArray(new Meal[0])).filter(item->item.getStrArea().toLowerCase().contains(searchTerm)).collect(Collectors.toList());

    }

    @Override
    public void showCategory(List<Categories> categories) {
        if (categories != null && categories.size() > 0) {
            Log.i(TAG, "showCategory: Categories received, count: " + categories.size());
            categoriesAdapter.setCategoriesList(categories);
            categoriesAdapter.notifyDataSetChanged();
        } else {
            Log.i(TAG, "showCategory: No categories received or empty list.");
        }
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
    public void showCountry(List<Meal> country) {
        countryAdapter.setCountryList(country);
        countryAdapter.notifyDataSetChanged();
        Log.i(TAG, "showCountries: ");
    }
}