package com.example.foodplanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.foodplanner.R;

import com.example.foodplanner.login.LoginActivity;
import com.example.foodplanner.signup.SignUpActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
//ActivityHomeBinding binding;
    String TAG="HomeActivity";
    private GoogleSignInClient gsc;
    private GoogleSignInOptions gso;
    FirebaseAuth auth= FirebaseAuth.getInstance();
    FirebaseUser currentUser = auth.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNav=findViewById(R.id.bottom_navigation);
        NavController navController= Navigation.findNavController(this,R.id.nav_host_fragment);
        bottomNav.setOnNavigationItemSelectedListener(item -> {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Please Register at First");
            builder.setTitle("Not Allowed for guest!");
            AlertDialog dialog=builder.create();

                if(item.getItemId()==R.id.homeFragment) {
                    navController.navigate(R.id.homeFragment);
                   // return true;
                    Log.i(TAG, "Home Fra: ");
                }
            else if(item.getItemId()== R.id.yourPlanFragment&&(currentUser != null)) {
                navController.navigate(R.id.yourPlanFragment);
                Log.i(TAG, "plan: ");

            }else if(item.getItemId()== R.id.favoriteFragment &&(currentUser != null)){
                    navController.navigate(R.id.favoriteFragment);
                    Log.i(TAG, "favorite: ");
                }
        else if(item.getItemId()== R.id.searchFragment){
            navController.navigate(R.id.searchFragment);
            Log.i(TAG, "search: ");
        }else{
                    dialog.show();
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