package com.example.foodplanner.detail.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.db.MealLocalDataSource;
import com.example.foodplanner.detail.presenter.DetailActivityPresenterImpl;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealPlan;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.network.MealRemoteDataSource;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import kotlinx.coroutines.MainCoroutineDispatcher;

public class DetailActivity extends AppCompatActivity implements DetailByIdView {
    TextView tv_category, tv_country, tv_instructions, tv_meal_name, tv_ingredient, tv_measure;
    ImageView mealImage;
    WebView webView;
    ImageButton btn_fav, btn_plan;
    DetailActivityPresenterImpl presenter;
    ImageButton btn_back;
    private static final String TAG = "DetailActivity";

    String planDate;
    MealPlan mealPlan;
    FirebaseAuth auth= FirebaseAuth.getInstance();
    FirebaseUser currentUser = auth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        btn_back=findViewById(R.id.btn_back_det);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        Intent intent = getIntent();
        Meal meal = (Meal) intent.getSerializableExtra("meal");

        presenter = new DetailActivityPresenterImpl(this,
                MealRepository.getInstance(MealRemoteDataSource.getInstance(),
                        MealLocalDataSource.getInstance(this)
                ));
        presenter.getMealById(meal.getIdMeal());
        tv_meal_name = findViewById(R.id.tv_meal_name);
        mealImage = findViewById(R.id.iv_meal);
        tv_category = findViewById(R.id.tv_category);
        tv_country = findViewById(R.id.tv_country);
        tv_instructions = findViewById(R.id.tv_instructions);
        tv_ingredient = findViewById(R.id.tv_ingrdiant);
        tv_measure = findViewById(R.id.tv_messure);
        webView = findViewById(R.id.videoView);
        //******************************************//
        btn_fav = findViewById(R.id.btn_det_fav);
        btn_plan = findViewById(R.id.btn_plan);
        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentUser!=null) {
                    addMeal(meal);
                    Toast.makeText(getBaseContext(), "Added to favorite", Toast.LENGTH_SHORT).show();
                }else{
                  showDialog();
                }
            }
        });

            btn_plan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(DetailActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int i, int i1, int i2) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(i, i1, i2);                                             //, yyyy
                            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd", Locale.getDefault());
                            String formattedDate = dateFormat.format(calendar.getTime());

                            planDate = formattedDate;
                            mealPlan = new MealPlan(planDate, meal);
                            addPlan(mealPlan);
                        }
                    }, year, month, day);
                    if(currentUser!=null) {
                        dialog.show();
                    }else{
                        showDialog();
                    }
                }

            });

    }

    private String extractYoutubeVideoId(String videoUrl) {
        String videoId = null;
        if (videoUrl != null && videoUrl.trim().length() > 0) {
            String[] separated = videoUrl.split("v=");
            videoId = separated[1];
            if (videoId.contains("&")) {
                String[] ampersandSplit = videoId.split("&");
                videoId = ampersandSplit[0];
            }
        }
        return videoId;
    }

    @Override
    public void showErrMsg(String error) {
        Log.i(TAG, "showErrMsg: ");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void addMeal(Meal meal) {
        presenter.addToFav(meal);
    }

    @Override
    public void addPlan(MealPlan mealPlan) {
    presenter.addToPlan(mealPlan);


    }


    @Override
    public void showMealById(List<Meal> meals) {
        if (meals != null && !meals.isEmpty()) {
            Meal meal = meals.get(0); // Assuming you only expect one meal
            // Populate UI elements with meal details
            tv_meal_name.setText(meal.getStrMeal());
            Glide.with(this).load(meal.getStrMealThumb()).placeholder(R.drawable.ic_launcher_foreground).into(mealImage);
            tv_category.setText(meal.getStrCategory());
            tv_country.setText(meal.getStrArea());
            tv_instructions.setText(meal.getStrInstructions());
            String videoUrl = meal.getStrYoutube();
            String videoId = extractYoutubeVideoId(videoUrl);
            String videoHtml = "<html><body style=\"margin:0;padding:0;\"><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/" + videoId + "\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe></body></html>";
            webView.loadDataWithBaseURL("https://www.youtube.com", videoHtml, "text/html", "utf-8", null);
            webView.getSettings().setJavaScriptEnabled(true);
            getIngAndMes(meal);

        }
    }


    public void getIngAndMes(Meal meal) {
        if (!meal.getStrIngredient1().isEmpty() && !Character.isWhitespace(meal.getStrIngredient1().charAt(0))) {
            tv_ingredient.append("\n \u2022" + meal.getStrIngredient1());
        }
        if (!meal.getStrIngredient2().isEmpty() && !Character.isWhitespace(meal.getStrIngredient2().charAt(0))) {
            tv_ingredient.append("\n \u2022" + meal.getStrIngredient2());
        }
        if (!meal.getStrIngredient3().isEmpty() && !Character.isWhitespace(meal.getStrIngredient3().charAt(0))) {
            tv_ingredient.append("\n \u2022" + meal.getStrIngredient3());
        }
        if (!meal.getStrIngredient4().isEmpty() && !Character.isWhitespace(meal.getStrIngredient4().charAt(0))) {
            tv_ingredient.append("\n \u2022" + meal.getStrIngredient4());
        }
        if (!meal.getStrIngredient5().isEmpty() && !Character.isWhitespace(meal.getStrIngredient5().charAt(0))) {
            tv_ingredient.append("\n \u2022" + meal.getStrIngredient5());
        }
        if (!meal.getStrIngredient6().isEmpty() && !Character.isWhitespace(meal.getStrIngredient6().charAt(0))) {
            tv_ingredient.append("\n \u2022 " + meal.getStrIngredient6());
        }
        if (!meal.getStrIngredient7().isEmpty() && !Character.isWhitespace(meal.getStrIngredient7().charAt(0))) {
            tv_ingredient.append("\n \u2022 " + meal.getStrIngredient7());
        }
        if (!meal.getStrIngredient8().isEmpty() && !Character.isWhitespace(meal.getStrIngredient8().charAt(0))) {
            tv_ingredient.append("\n \u2022 " + meal.getStrIngredient8());
        }
        if (!meal.getStrIngredient9().isEmpty() && !Character.isWhitespace(meal.getStrIngredient9().charAt(0))) {
            tv_ingredient.append("\n \u2022 " + meal.getStrIngredient9());
        }
        if (!meal.getStrIngredient10().isEmpty() && !Character.isWhitespace(meal.getStrIngredient10().charAt(0))) {
            tv_ingredient.append("\n \u2022 " + meal.getStrIngredient10());
        }
        if (!meal.getStrIngredient11().isEmpty() && !Character.isWhitespace(meal.getStrIngredient11().charAt(0))) {
            tv_ingredient.append("\n \u2022 " + meal.getStrIngredient11());
        }
        if (!meal.getStrIngredient12().isEmpty() && !Character.isWhitespace(meal.getStrIngredient12().charAt(0))) {
            tv_ingredient.append("\n \u2022 " + meal.getStrIngredient12());
        }
        if (!meal.getStrIngredient13().isEmpty() && !Character.isWhitespace(meal.getStrIngredient13().charAt(0))) {
            tv_ingredient.append("\n \u2022 " + meal.getStrIngredient13());
        }
        if (!meal.getStrIngredient14().isEmpty() && !Character.isWhitespace(meal.getStrIngredient14().charAt(0))) {
            tv_ingredient.append("\n \u2022 " + meal.getStrIngredient14());
        }
        if (!meal.getStrIngredient15().isEmpty() && !Character.isWhitespace(meal.getStrIngredient15().charAt(0))) {
            tv_ingredient.append("\n \u2022 " + meal.getStrIngredient15());
        }
        if (!meal.getStrIngredient16().isEmpty() && !Character.isWhitespace(meal.getStrIngredient16().charAt(0))) {
            tv_ingredient.append("\n \u2022 " + meal.getStrIngredient16());
        }
        if (!meal.getStrIngredient17().isEmpty() && !Character.isWhitespace(meal.getStrIngredient17().charAt(0))) {
            tv_ingredient.append("\n \u2022 " + meal.getStrIngredient17());
        }
        if (!meal.getStrIngredient18().isEmpty() && !Character.isWhitespace(meal.getStrIngredient18().charAt(0))) {
            tv_ingredient.append("\n \u2022 " + meal.getStrIngredient18());
        }
        if (!meal.getStrIngredient19().isEmpty() && !Character.isWhitespace(meal.getStrIngredient19().charAt(0))) {
            tv_ingredient.append("\n \u2022 " + meal.getStrIngredient19());
        }
        if (!meal.getStrIngredient20().isEmpty() && !Character.isWhitespace(meal.getStrIngredient20().charAt(0))) {
            tv_ingredient.append("\n \u2022 " + meal.getStrIngredient20());
        }

        //-----------Meassure
        if (!meal.getStrMeasure1().isEmpty() && !Character.isWhitespace(meal.getStrMeasure1().charAt(0))) {
            tv_measure.append("\n: " + meal.getStrMeasure1());
        }
        if (!meal.getStrMeasure2().isEmpty() && !Character.isWhitespace(meal.getStrMeasure2().charAt(0))) {
            tv_measure.append("\n: " + meal.getStrMeasure2());
        }
        if (!meal.getStrMeasure3().isEmpty() && !Character.isWhitespace(meal.getStrMeasure3().charAt(0))) {
            tv_measure.append("\n: " + meal.getStrMeasure3());
        }
        if (!meal.getStrMeasure4().isEmpty() && !Character.isWhitespace(meal.getStrMeasure4().charAt(0))) {
            tv_measure.append("\n: " + meal.getStrMeasure4());
        }
            if (!meal.getStrMeasure5().isEmpty() && !Character.isWhitespace(meal.getStrMeasure5().charAt(0))) {
                tv_measure.append("\n: " + meal.getStrMeasure5());
            }
            if (!meal.getStrMeasure6().isEmpty() && !Character.isWhitespace(meal.getStrMeasure6().charAt(0))) {
                tv_measure.append("\n: " + meal.getStrMeasure6());
            }
                if (!meal.getStrMeasure7().isEmpty() && !Character.isWhitespace(meal.getStrMeasure7().charAt(0))) {
                    tv_measure.append("\n: " + meal.getStrMeasure7());
                }
                    if (!meal.getStrMeasure8().isEmpty() && !Character.isWhitespace(meal.getStrMeasure8().charAt(0))) {
                        tv_measure.append("\n: " + meal.getStrMeasure8());
                    }
                        if (!meal.getStrMeasure9().isEmpty() && !Character.isWhitespace(meal.getStrMeasure9().charAt(0))) {
                            tv_measure.append("\n: " + meal.getStrMeasure9());}
                            if (!meal.getStrMeasure10().isEmpty() && !Character.isWhitespace(meal.getStrMeasure10().charAt(0))) {
                                tv_measure.append("\n: " + meal.getStrMeasure10());


                            }
//
        if (!meal.getStrMeasure11().isEmpty() && !Character.isWhitespace(meal.getStrMeasure11().charAt(0))) {
            tv_measure.append("\n: " + meal.getStrMeasure11());


        }

        if (!meal.getStrMeasure12().isEmpty() && !Character.isWhitespace(meal.getStrMeasure10().charAt(0))) {
            tv_measure.append("\n: " + meal.getStrMeasure12());


        }

        if (!meal.getStrMeasure13().isEmpty() && !Character.isWhitespace(meal.getStrMeasure13().charAt(0))) {
            tv_measure.append("\n: " + meal.getStrMeasure13());


        }

        if (!meal.getStrMeasure14().isEmpty() && !Character.isWhitespace(meal.getStrMeasure14().charAt(0))) {
            tv_measure.append("\n: " + meal.getStrMeasure14());


        }

        if (!meal.getStrMeasure15().isEmpty() && !Character.isWhitespace(meal.getStrMeasure15().charAt(0))) {
            tv_measure.append("\n: " + meal.getStrMeasure15());


        }

        if (!meal.getStrMeasure16().isEmpty() && !Character.isWhitespace(meal.getStrMeasure16().charAt(0))) {
            tv_measure.append("\n: " + meal.getStrMeasure16());


        }
        if (!meal.getStrMeasure17().isEmpty() && !Character.isWhitespace(meal.getStrMeasure17().charAt(0))) {
            tv_measure.append("\n: " + meal.getStrMeasure17());


        }
        if (!meal.getStrMeasure18().isEmpty() && !Character.isWhitespace(meal.getStrMeasure18().charAt(0))) {
            tv_measure.append("\n: " + meal.getStrMeasure18());


        }
        if (!meal.getStrMeasure19().isEmpty() && !Character.isWhitespace(meal.getStrMeasure19().charAt(0))) {
            tv_measure.append("\n: " + meal.getStrMeasure19());


        }
        if (!meal.getStrMeasure20().isEmpty() && !Character.isWhitespace(meal.getStrMeasure20().charAt(0))) {
            tv_measure.append("\n: " + meal.getStrMeasure20());


        }








    }

public void showDialog(){
    AlertDialog.Builder builder=new AlertDialog.Builder(this);
    builder.setMessage("Please Register at First");
    builder.setTitle("Not Allowed for guest!");
    AlertDialog dialog=builder.create();
    dialog.show();
}
                    }
