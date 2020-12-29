package com.example.aatchala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {
    private Session session;
    private UserViewModel nUserViewModel;
    private CartHandler cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.LauncherTheme);
        super.onCreate(savedInstanceState);
        nUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        session = new Session(getApplicationContext());
        cart = new CartHandler();

        //Log.d("outside if", session.getUserId());
        if (session.getUserId().matches("")) {
            //Log.d("inside if", session.getUserId());
            session.createLogoutSession();
        }

        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        finish();
    }
}