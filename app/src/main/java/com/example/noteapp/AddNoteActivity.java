package com.example.noteapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityAddNoteBinding;
import com.example.noteapp.model.Note;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNoteActivity extends BaseActivity<ActivityAddNoteBinding> {

    private Note note;

    @Override
    protected ActivityAddNoteBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityAddNoteBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.add_new_note_activity_title);

        if (getIntent().hasExtra("note")) {
            note = (Note) getIntent().getSerializableExtra("note");
            binding.createBtn.setText("Update");
            setTitle("Edit Note");
            binding.title.setText(note.getTitle());
            binding.content.setText(note.getContent());
        }

        binding.createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = binding.title.getText().toString();
                String content = binding.content.getText().toString();

                if (note == null) {

                    note = new Note();
                    note.setTitle(title);
                    note.setContent(content);
                    //dataBaseHelper.addNote(note);
                    Call<Note> call = mainApi.createNote(note);
                    call.enqueue(new Callback<Note>() {
                        @Override
                        public void onResponse(Call<Note> call, Response<Note> response) {
                            Log.d("Note", response.body().toString()+"is created");
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Note> call, Throwable t) {

                        }
                    });
                } else {
                    note.setTitle(title);
                    note.setContent(content);
                    dataBaseHelper.updateNote(note);
                    finish();
                }
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
