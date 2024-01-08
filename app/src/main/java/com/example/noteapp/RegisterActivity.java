package com.example.noteapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityRegisterBinding;
import com.example.noteapp.model.User;
import com.example.noteapp.remote.MainApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> {


    @Override
    protected ActivityRegisterBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityRegisterBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Registration");

        binding.completeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String firstname = binding.firstNameEditText.getText().toString();
                String lastname = binding.lastNameEditText.getText().toString();
                String email = binding.emailEditText.getText().toString();
                String passcode = binding.passwordEditText.getText().toString();
                if (!firstname.isEmpty() && !lastname.isEmpty()) {

//                    preferenceManager.setValue("firstname", firstname);
//                    preferenceManager.setValue("lastname", lastname);
//                    preferenceManager.setValue("email", email);
//                    preferenceManager.setValue("passcode", passcode);
//                    preferenceManager.setValue("isLogin", true);
                      User user = new User(firstname, lastname, email,passcode);
                      Call<User> call=mainApi.createUser(user);
                      call.enqueue(new Callback<User>() {
                          @Override
                          public void onResponse(Call<User> call, Response<User> response) {
                              User createdUser = response.body();
                              preferenceManager.setValue("isLogin", true);
                              preferenceManager.setValue("access_token", createdUser.getAccessToken());
                              Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                              startActivity(intent);
                          }

                          @Override
                          public void onFailure(Call<User> call, Throwable t) {

                          }
                      });
                      
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);

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
