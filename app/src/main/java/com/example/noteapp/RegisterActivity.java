package com.example.noteapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityRegisterBinding;
import com.example.noteapp.model.User;
import com.example.noteapp.remote.MainApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> {

    private String deviceToken;

    @Override
    protected ActivityRegisterBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityRegisterBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Registration");
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {
                    deviceToken = task.getResult();
                    Log.d("Device token", deviceToken);
                }
            }
        });

        binding.completeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String firstname = binding.firstNameEditText.getText().toString();
                String lastname = binding.lastNameEditText.getText().toString();
                String email = binding.emailEditText.getText().toString();
                String passcode = binding.passwordEditText.getText().toString();
                if (!firstname.isEmpty() && !lastname.isEmpty()) {


                    User user = new User(firstname, lastname, email, passcode);
                    user.setDeviceToken(deviceToken);
                    Log.d("User", new Gson().toJson(user));

                    Call<User> call = mainApi.createUser(user);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.isSuccessful()) {
                                User createdUser = response.body();
                                preferenceManager.setValue("isLogin", true);
                                preferenceManager.setValue("access_token", createdUser.getAccessToken());
                                preferenceManager.setValue("firstname", firstname);
                                preferenceManager.setValue("lastname", lastname);
                                preferenceManager.setValue("email", email);
                                preferenceManager.setValue("passcode", passcode);
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });

                }
            }
        });
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }


    @Override
    public boolean hasActionBar() {
        return true;
    }
}
