package com.example.foodplanner.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;

import com.example.foodplanner.R;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
//ActivityHomeBinding binding;
    String TAG="HomeActivity";
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
//        binding=ActivityHomeBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        binding.bottomNavigation.setBackground(null);
//        binding.bottomNavigation.setOnItemSelectedListener(item->{
//            switch(item.getItemId()){
//                case R.id.nav_home:
//                    replaceFragment(new HomeFragment());
//                    break;
//                    case R.id.nav_add :
//                        replaceFragment(new AddRecipeFragment());
//                        break;
//                case R.id.nav_plan :
//                    replaceFragment(new YourPlanFragment());
//                    break;
//
//            }
//            return true;
//        });

    }
//    private void replaceFragment(Fragment fragment){
//        FragmentManager fragmentManager=getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.bottom_navigation,fragment);
//        fragmentTransaction.commit();
//    }
}