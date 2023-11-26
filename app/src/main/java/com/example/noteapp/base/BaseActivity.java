package com.example.noteapp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity {

    protected VB binding;

    protected abstract VB inflateViewBinding(LayoutInflater inflater);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = inflateViewBinding(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}
