package com.example.foodplanner;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.signup.SignUpActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogoutFragment extends Fragment {


    public LogoutFragment() {
        // Required empty public constructor
    }
    private TextView emailTextView;
    private static final String TAG = "LogoutFragment";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view =inflater.inflate(R.layout.fragment_logout, container, false);
        ImageView logoutButton = view.findViewById(R.id.logoutButton);
        emailTextView = view.findViewById(R.id.emailTextView);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        GoogleSignInAccount acct= GoogleSignIn.getLastSignedInAccount(getContext());

        if (user != null) {
           // String name = user.getDisplayName();
            String email = user.getEmail();

            if (email != null && !email.isEmpty()) {
                emailTextView.setText(email);
                Log.i(TAG, "User Email: "+user.getEmail());
            } else {
                emailTextView.setText("Email not available");

            }
        }
        else if(acct!=null){
            String email = acct.getEmail();

            if (email != null && !email.isEmpty()) {
                emailTextView.setText(email);
                Log.i(TAG, "User Email: "+acct.getEmail());
            } else {
                emailTextView.setText("Email not available");

            }
        }

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                Toast.makeText(getContext(), "Log out Successfully: " , Toast.LENGTH_SHORT).show();

            }
        });
         return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseApp.initializeApp(requireContext());
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();

        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(requireContext(),
                GoogleSignInOptions.DEFAULT_SIGN_IN);
        googleSignInClient.signOut().addOnCompleteListener(requireActivity(),
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Start SignUpActivity
                        Intent intent = new Intent(requireContext(), SignUpActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        requireActivity().finish(); // Optional: Finish the current activity
                    }
                });

        Toast.makeText(getContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();
    }



}