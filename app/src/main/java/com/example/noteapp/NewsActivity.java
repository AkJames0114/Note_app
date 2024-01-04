package com.example.noteapp;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityNewsBinding;

public class NewsActivity extends BaseActivity<ActivityNewsBinding> {

    @Override
    protected ActivityNewsBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityNewsBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean hasActionBar() {
        return true;
    }

    @Override
    public boolean hasBackButton() {
        return true;
    }

    @Override
    public boolean backButtonClickActivated() {
        return false;
    }

    @Override
    public int getBackButtonIcon() {
        return R.drawable.ic_home;
    }
}
