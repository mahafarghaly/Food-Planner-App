package com.example.foodplanner.homefrag.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
    FirebaseAuth auth= FirebaseAuth.getInstance();
    FirebaseUser currentUser = auth.getCurrentUser();
    ConstraintLayout constraintLayout;
    LottieAnimationView lottieAnimationView;
    TextView tvDaily,tvCategory,tvCountry;
    View divider1,divider2;
    ProgressBar progressBar;
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
        constraintLayout=view.findViewById(R.id.con_layout_home);
        tvCategory=view.findViewById(R.id.tv_home_categories);
        tvCountry=view.findViewById(R.id.tv_home_countries);
        tvDaily=view.findViewById(R.id.tv_insp_meal);
        divider1=view.findViewById(R.id.divider_meal);
        divider2=view.findViewById(R.id.divider_cate);
        lottieAnimationView=view.findViewById(R.id.lottieAnimationView);
        lottieAnimationView.setVisibility(View.GONE);
        progressBar=view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
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
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrMsg(String error) {
        progressBar.setVisibility(View.GONE);
        tvCategory.setVisibility(View.GONE);
        tvCountry.setVisibility(View.GONE);
         tvDaily.setVisibility(View.GONE);
         divider1.setVisibility(View.GONE);
        divider2.setVisibility(View.GONE);
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
        lottieAnimationView.playAnimation();


    }

    @Override
    public void addMeal(Meal meal) {
if(currentUser!=null) {
    homeFragPresenter.addToFav(meal);
}
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
        if(currentUser!=null) {
            addMeal(meal);
            Toast.makeText(getContext(), "Added to favorite", Toast.LENGTH_SHORT).show();
        }else{
            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
            builder.setMessage("Please Register at First");
            builder.setTitle("Not Allowed for guest!");
            AlertDialog dialog=builder.create();
            dialog.show();
        }
    }


}
