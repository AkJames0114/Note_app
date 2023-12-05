package com.example.noteapp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;

import com.example.noteapp.R;
import com.example.noteapp.db.DataBaseHelper;
import com.example.noteapp.util.PreferenceManager;

public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity {

    protected VB binding;

    public PreferenceManager preferenceManager;
    public DataBaseHelper dataBaseHelper;


    protected abstract VB inflateViewBinding(LayoutInflater inflater);

    public boolean hasActionBar() {
        return false;
    }
    public boolean hasBackButton(){
        return false;
    }
    public int getBackButtonIcon(){
        return 0;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = inflateViewBinding(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = PreferenceManager.getInstance(this);
        dataBaseHelper = new DataBaseHelper(this);

        if (hasActionBar()) {
            Toolbar toolbar = binding.getRoot().findViewById(R.id.toolbar);
            if (toolbar != null)
                setSupportActionBar(toolbar);
        }
        if (hasBackButton()){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (getBackButtonIcon()!=0)
            {
                getSupportActionBar().setHomeAsUpIndicator(getDrawable(getBackButtonIcon()));
            }
        }
    }
    public void setTitle(int titleId){
        if (getSupportActionBar()!=null)
            getSupportActionBar().setTitle(titleId);
    }
    public void setTitle(String title){
        if (getSupportActionBar()!=null)
            getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
