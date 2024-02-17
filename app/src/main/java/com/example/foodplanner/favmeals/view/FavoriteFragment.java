package com.example.foodplanner.favmeals.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.db.AppDatabase;
import com.example.foodplanner.db.MealDAO;
import com.example.foodplanner.db.MealLocalDataSource;
import com.example.foodplanner.favmeals.presenter.FavPresenter;
import com.example.foodplanner.favmeals.presenter.FavPresenterImpl;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.network.MealRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class FavoriteFragment extends Fragment implements FavMealView,OnFavoriteClickListener  {

    public FavoriteFragment() {
        // Required empty public constructor
    }
    RecyclerView favRecyclerView;
    FavoriteAdapter favAdapter;

    FavPresenter favPresenter;
    LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        favRecyclerView = view.findViewById(R.id.rv_favorite); // Find RecyclerView here
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayoutManager=new LinearLayoutManager(getContext());
        favAdapter=new FavoriteAdapter(getContext(),new ArrayList<>(),this);
        favPresenter=new FavPresenterImpl(this,
                MealRepository.getInstance(MealRemoteDataSource.getInstance(),
                        MealLocalDataSource.getInstance(getContext())));
        favRecyclerView.setLayoutManager(linearLayoutManager);
        favRecyclerView.setAdapter(favAdapter);
        favPresenter.getStoredMeals();
        showData();


    }

    @Override
    public void showData() {
        Flowable<List<Meal>> data=  favPresenter.getStoredMeals();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals ->{
                    favAdapter.setList( meals);
                    favAdapter.notifyDataSetChanged();
                } );

    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    @Override
    public void removeMeal(Meal meal) {

        favPresenter.removeFromFav(meal);
        Toast.makeText(getContext(),"Removed from favorite",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFavoriteMealClick(Meal meal) {

        removeMeal(meal);

    }
}