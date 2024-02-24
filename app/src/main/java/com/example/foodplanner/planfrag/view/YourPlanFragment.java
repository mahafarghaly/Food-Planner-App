package com.example.foodplanner.planfrag.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.foodplanner.R;
import com.example.foodplanner.db.MealLocalDataSource;
import com.example.foodplanner.favmeals.presenter.FavPresenter;
import com.example.foodplanner.favmeals.presenter.FavPresenterImpl;
import com.example.foodplanner.favmeals.view.FavoriteAdapter;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealPlan;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.network.MealRemoteDataSource;
import com.example.foodplanner.planfrag.presenter.PlanPresenter;
import com.example.foodplanner.planfrag.presenter.PlanPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class YourPlanFragment extends Fragment implements  PlanView,OnPlanClickListener {


    public YourPlanFragment() {
        // Required empty public constructor
    }

    RecyclerView planRecyclerView;
    PlanAdapter planAdapter;

    PlanPresenter planPresenter;
    LinearLayoutManager linearLayoutManager;
    LottieAnimationView lottieAnimationView;
TextView tv_no_plan;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_your_plan, container, false);
        planRecyclerView = view.findViewById(R.id.rv_plan);
        tv_no_plan= view.findViewById(R.id.tv_noPlan);
        lottieAnimationView = view.findViewById(R.id.lottiePlan);
        lottieAnimationView.setVisibility(View.GONE);
        tv_no_plan.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(getContext());
        planAdapter = new PlanAdapter(getContext(), new ArrayList<>(), this);
        planPresenter = new PlanPresenterImpl(this,
                MealRepository.getInstance(MealRemoteDataSource.getInstance(),
                        MealLocalDataSource.getInstance(getContext())));
        planRecyclerView.setLayoutManager(linearLayoutManager);
        planRecyclerView.setAdapter(planAdapter);
        planPresenter.getStoredMealPlan();
        showData();
    }

    @Override
    public void showData() {
        Flowable<List<MealPlan>> data = planPresenter.getStoredMealPlan();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {
                    if(meals.size()>0) {
                        planAdapter.setList(meals);
                        planAdapter.notifyDataSetChanged();
                    }else{
                        planAdapter.notifyDataSetChanged();
                        planRecyclerView.setVisibility(View.GONE);
                        tv_no_plan.setVisibility(View.VISIBLE);
                lottieAnimationView.setVisibility(View.VISIBLE);
                        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
                lottieAnimationView.playAnimation();

                    }
                });
    }



    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    @Override
    public void removeMealPlan(MealPlan mealPlan) {
        planPresenter.removeFromPlan(mealPlan);
        Toast.makeText(getContext(),"Removed from Plan",Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onPlanMealClick(MealPlan mealPlan) {
        removeMealPlan(mealPlan);
    }
}