package com.example.noteapp;

import android.view.LayoutInflater;
import android.view.View;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    String[] passCodeString = new String[4];

    int passCodeCounter = 0;

    @Override
    protected ActivityLoginBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityLoginBinding.inflate(inflater);
    }


    public void numberClicked(View view){

    }

}
