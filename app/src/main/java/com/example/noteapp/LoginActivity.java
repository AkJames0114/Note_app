package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityLoginBinding;

import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.util.Arrays;

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
    }

    public void numberClicked(View view) {
        if (passCodeCounter > 3) {
            return;
        }
        String text = ((TextView) view).getText().toString();
        passCodeString[passCodeCounter] = text;

        switch (passCodeCounter) {
            case 0:
                binding.passcode1.setText("*");
                break;
            case 1:
                binding.passcode2.setText("*");
                break;
            case 2:
                binding.passcode3.setText("*");
                break;
            case 3:
                binding.passcode4.setText("*");
                break;

        }
        passCodeCounter++;

        if (passCodeCounter > 3) {
            checkPassword(passCodeString);
        }

    }

    private void checkPassword(String[] passCodeString) {
        String passcode = convertPassCodeArrayToString(passCodeString);
        String myPasscode = (String) preferenceManager.getValue(String.class, "passcode", "");

        if (!myPasscode.isEmpty() && !passcode.isEmpty()) {
            if (myPasscode.equals(passcode)) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    private String convertPassCodeArrayToString(String[] passCodeString) {
        StringBuilder passcode = new StringBuilder();
        for (String s : passCodeString) {
            passcode.append(s);
        }
        return passcode.toString();
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
