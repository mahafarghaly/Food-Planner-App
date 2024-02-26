package com.example.foodplanner.signup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.HomeActivity;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.login.LoginActivity;
import com.example.foodplanner.signup.presnter.SignUpPresenter;
import com.example.foodplanner.signup.presnter.SignUpPresenterImpl;
import com.example.foodplanner.signup.view.SignUpView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.checkerframework.checker.signature.qual.SignatureUnknown;

public class SignUpActivity extends AppCompatActivity implements SignUpView {
    private SignUpPresenter presenter;
    private FirebaseAuth auth;
    private EditText signupEmail, signupPassword, confPassword;
    private Button signUpButton,btnSkip;
    private TextView loginRedirectText;
    private ImageView signGoogleBtn;
    private static final String TAG = "SignUpActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        auth = FirebaseAuth.getInstance();
        GoogleSignInAccount acct=GoogleSignIn.getLastSignedInAccount(this);

        FirebaseUser currentUser = auth.getCurrentUser();

        presenter = new SignUpPresenterImpl(this);


        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        confPassword = findViewById(R.id.signup_Confpassword);
        signUpButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signGoogleBtn = findViewById(R.id.btn_google);
        btnSkip=findViewById(R.id.btn_skip);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,HomeActivity.class));
            }
        });
        signUpButton.setOnClickListener(v -> {
            String email = signupEmail.getText().toString().trim();
            String password = signupPassword.getText().toString().trim();
            String confirmPassword = confPassword.getText().toString().trim();
            presenter.signUp(email, password, confirmPassword);
        });

        loginRedirectText.setOnClickListener(v-> startActivity(new Intent(SignUpActivity.this, LoginActivity.class)));


        signGoogleBtn.setOnClickListener(v -> presenter.handleGoogleSignIn());
        if (currentUser != null||acct!=null ) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    public void showSignUpSuccess() {
        Toast.makeText(this, "SignUp Successful", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void showSignUpFailure(String message) {
        Toast.makeText(this, "SignUp Failed: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }
}
