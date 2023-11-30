package com.example.noteapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivitySplashBinding;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    private Boolean isRegistered = false;

    @Override
    protected ActivitySplashBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivitySplashBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isRegistered = (Boolean) preferenceManager.getValue(Boolean.class, "isRegistered",false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isRegistered) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }

            }
        }, 4000);


    }

}
