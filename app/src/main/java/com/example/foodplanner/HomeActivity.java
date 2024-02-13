package com.example.foodplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;

import com.example.foodplanner.R;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
//ActivityHomeBinding binding;
    String TAG="HomeActivity";
    private GoogleSignInClient gsc;
    private GoogleSignInOptions gso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNav=findViewById(R.id.bottom_navigation);
        NavController navController= Navigation.findNavController(this,R.id.nav_host_fragment);
        bottomNav.setOnNavigationItemSelectedListener(item -> {
           // switch (item.getItemId()){

                if(item.getItemId()==R.id.homeFragment) {
                    navController.navigate(R.id.homeFragment);
                   // return true;
                    Log.i(TAG, "Home Fra: ");
                }
           else if(item.getItemId()==R.id.addRecipeFragment) {
                navController.navigate(R.id.addRecipeFragment);
                //return true;}
                Log.i(TAG, "add Frag: ");
            }
            else if(item.getItemId()== R.id.yourPlanFragment) {
                navController.navigate(R.id.yourPlanFragment);
                Log.i(TAG, "plan: ");

            }
      return true;

        });
        //google SignIn
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acct=GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
        String personame= acct.getDisplayName();
        String personEmail=acct.getEmail();
           // String personName=acct.getDisplayName();
            //any data want to display
        }
    }



}