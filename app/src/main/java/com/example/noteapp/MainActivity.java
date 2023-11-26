package com.example.noteapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> {


    @Override
    protected ActivityMainBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}