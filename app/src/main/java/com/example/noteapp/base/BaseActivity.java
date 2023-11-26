package com.example.noteapp.base;

import android.view.LayoutInflater;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity {

    VB binding;

    abstract VB inflateViewBinding(LayoutInflater inflater);


}
