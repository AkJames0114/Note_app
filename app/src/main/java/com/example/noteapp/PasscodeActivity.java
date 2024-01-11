package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityLoginBinding;
import com.example.noteapp.databinding.ActivityPasscodeBinding;

public class PasscodeActivity extends BaseActivity<ActivityPasscodeBinding> {

    String[] passCodeString = new String[4];

    int passCodeCounter = 0;

    @Override
    protected ActivityPasscodeBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityPasscodeBinding.inflate(inflater);
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
                preferenceManager.setValue("isLogin", true);
                Intent intent = new Intent(PasscodeActivity.this, NewsActivity.class);
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
