package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private NoteFragment noteFragment;
    private ProfileFragment profileFragment;


    @Override
    protected ActivityMainBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.notes);
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                replaceFragment(item.getItemId());

                return true;

            }
        });
    }


    private void replaceFragment(int tabId) {
        if (tabId == R.id.noteTab) {
            if (noteFragment == null)
                noteFragment = new NoteFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, noteFragment).commit();
            setTitle(R.string.notes);
        } else {
            if (profileFragment == null)
                profileFragment = new ProfileFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, profileFragment).commit();
            setTitle(R.string.profile);

        }
    }
    @Override
    public boolean hasActionBar() {
        return true;
    }
}
