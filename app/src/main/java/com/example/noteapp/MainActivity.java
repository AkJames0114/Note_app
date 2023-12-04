package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityMainBinding;
import com.example.noteapp.model.Note;
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
        replaceFragment(R.id.noteTab);
        binding.addNoteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class );
                startActivity(intent);

            }
        });

        //generateNotes();
    }

    public void generateNotes() {

        for (int i = 0; i < 100; i++) {
            Note note = new Note("Title " + (i + 1), "CONTENT " + (i + 1) + "; A plain text editor that allows you to keep notes throughout the day, create a list, write or edit code without worrying about unwanted auto formatting.");
            Log.d("Note: ", note.toString());
            dataBaseHelper.addNote(note);

        }
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
