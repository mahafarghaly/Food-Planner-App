package com.example.foodplanner.login.presenter;

import android.content.Intent;

public interface LoginPresenter {
    void login(String email, String password);
    void handleGoogleSignIn();
    void onActivityResult(int requestCode, int resultCode, Intent data);
}
