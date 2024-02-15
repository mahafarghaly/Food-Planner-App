package com.example.foodplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodplanner.signup.SignUpActivity;

public class SplashScreenActivity extends AppCompatActivity {
TextView appName;
LottieAnimationView lottie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);
//    appName=findViewById(R.id.appname);
    lottie=findViewById(R.id.lottie);
   // appName.animate().translationY(-1400).setDuration(2700).setStartDelay(0);
    lottie.animate().translationX(2000).setDuration(2000).setStartDelay(2000);
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent i=new Intent(getApplicationContext(), SignUpActivity.class);
            startActivity(i);
        }
    },3700);

    }
}