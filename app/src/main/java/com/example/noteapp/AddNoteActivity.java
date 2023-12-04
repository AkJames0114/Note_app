package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityAddNoteBinding;
import com.example.noteapp.model.Note;

public class AddNoteActivity extends BaseActivity<ActivityAddNoteBinding> {
    @Override
    protected ActivityAddNoteBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityAddNoteBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.add_new_note_activity_title);

        binding.createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.title.getText().toString();
                String content = binding.content.getText().toString();

                Note note = new Note();
                note.setTitle(title);
                note.setContent(content);
                dataBaseHelper.addNote(note);
                finish();
            }
        });
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
