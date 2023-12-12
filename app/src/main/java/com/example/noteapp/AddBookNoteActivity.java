package com.example.noteapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityAddBookNoteBinding;
import com.example.noteapp.databinding.ActivityAddNoteBinding;
import com.example.noteapp.model.BookNote;
import com.example.noteapp.model.Note;

public class AddBookNoteActivity extends BaseActivity<ActivityAddBookNoteBinding> {

    private BookNote bookNote;

    @Override
    protected ActivityAddBookNoteBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityAddBookNoteBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.add_new_book_note_activity_title);

        if (getIntent().hasExtra("book_note")) {
            bookNote = (BookNote) getIntent().getSerializableExtra("book_note");
            binding.createBtn.setText("Update");
            setTitle("Edit Book_Note");
            binding.title.setText(bookNote.getTitle());
            binding.content.setText(bookNote.getContent());
        }

        binding.createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = binding.title.getText().toString();
                String content = binding.content.getText().toString();

                if (bookNote == null) {

                    bookNote = new BookNote();
                    bookNote.setTitle(title);
                    bookNote.setContent(content);
                    dataBaseHelper.addBookNote(bookNote);
                } else {
                    bookNote.setTitle(title);
                    bookNote.setContent(content);
                    dataBaseHelper.updateBookNote(bookNote);
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
