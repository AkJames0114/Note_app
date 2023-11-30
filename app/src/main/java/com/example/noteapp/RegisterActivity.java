package com.example.noteapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityRegisterBinding;

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
                String passcode = binding.passCodeEditText.getText().toString();
                if (!firstname.isEmpty() && !lastname.isEmpty() && !email.isEmpty() && !passcode.isEmpty()) {

                    preferenceManager.setValue("firstname", firstname);
                    preferenceManager.setValue("lastname", lastname);
                    preferenceManager.setValue("email", email);
                    preferenceManager.setValue("passcode", passcode);
                    preferenceManager.setValue("isRegistered", true);

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);

                }
            }
        });
    }

    @Override
    public boolean hasActionBar() {
        return true;
    }
}
