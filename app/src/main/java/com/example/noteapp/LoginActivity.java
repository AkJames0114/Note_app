package com.example.noteapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityLoginBinding;
import com.example.noteapp.model.Token;
import com.example.noteapp.model.User;
import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    String[] passCodeString = new String[4];

    int passCodeCounter = 0;

    @Override
    protected ActivityLoginBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityLoginBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Login");
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.emailEditText.getText().toString();
                String password = binding.passwordEditText.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {

                    User user = new User();
                    user.setEmail(email);
                    user.setPassword(password);
                    Log.d("User", new Gson().toJson(user));

                    Call<Token> call = mainApi.login(user);
                    call.enqueue(new Callback<Token>() {
                        @Override
                        public void onResponse(Call<Token> call, Response<Token> response) {
                            Token token = response.body();
                            preferenceManager.setValue("isLogin", true);
                            preferenceManager.setValue("access_token", token.getAccess());
                            saveToFile(token.getAccess(), token.getRefresh());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);


                        }

                        @Override
                        public void onFailure(Call<Token> call, Throwable t) {

                        }
                    });


                }
            }
        });
    }

    private void saveToFile(String access_token, String refresh) {
        String filename = "my_access_token.txt";
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(access_token.getBytes());
            outputStream.write("\n".getBytes());
            outputStream.write(refresh.getBytes());
            outputStream.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public boolean hasActionBar() {
        return true;
    }

    @Override
    public boolean hasBackButton() {
        return true;
    }
}
