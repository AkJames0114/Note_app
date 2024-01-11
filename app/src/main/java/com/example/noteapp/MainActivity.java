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
import com.example.noteapp.model.BookNote;
import com.example.noteapp.model.Note;
import com.google.android.material.navigation.NavigationBarView;

import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private NoteFragment noteFragment;
    private ProfileFragment profileFragment;
    private LibraryFragment libraryFragment;
    private int selectedTab = R.id.noteTab;

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
                if (selectedTab == R.id.noteTab) {

                    Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                    startActivity(intent);

                } else if (selectedTab == R.id.libraryTab) {
                    Intent intent = new Intent(MainActivity.this, AddBookNoteActivity.class);
                    startActivity(intent);
                }
            }
        });

        String access_refresh_token = getAccessTokenFromFile();
        Log.d("MainActivity","Token: "+access_refresh_token);

        /*generateNotes();*/
        //generateBookNotes();
    }

    public String getAccessTokenFromFile() {

        String filename = "my_access_token.txt";
        FileInputStream inputStream;

        StringBuilder builder = new StringBuilder();
        try {
            inputStream = openFileInput(filename);
            int c;

            while ((c = inputStream.read()) != -1) {
                builder.append((char) c);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  builder.toString();

    }

    public void generateNotes() {

        for (int i = 0; i < 5; i++) {
            Note note = new Note("Title " + (i + 1), "CONTENT " + (i + 1) + "; A plain text editor that allows you to keep notes throughout the day, create a list, write or edit code without worrying about unwanted auto formatting.");
            Log.d("Note: ", note.toString());
            dataBaseHelper.addNote(note);

        }
    }

    public void generateBookNotes() {

        for (int i = 0; i < 5; i++) {
            BookNote bookNote = new BookNote("Title " + (i + 1), "CONTENT " + (i + 1) + "; A plain text editor that allows you to keep notes throughout the day, create a list, write or edit code without worrying about unwanted auto formatting.");
            Log.d("Book Note: ", bookNote.toString());
            dataBaseHelper.addBookNote(bookNote);

        }
    }

    private void replaceFragment(int tabId) {
        selectedTab = tabId;
        if (tabId == R.id.noteTab) {
            binding.addNoteIcon.setVisibility(View.VISIBLE);
            if (noteFragment == null)
                noteFragment = new NoteFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, noteFragment).commit();
            setTitle(R.string.notes);
        } else if (tabId == R.id.libraryTab) {
            binding.addNoteIcon.setVisibility(View.VISIBLE);
            if (libraryFragment == null)
                libraryFragment = new LibraryFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, libraryFragment).commit();
            setTitle("Library");
        } else {
            binding.addNoteIcon.setVisibility(View.GONE);
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
