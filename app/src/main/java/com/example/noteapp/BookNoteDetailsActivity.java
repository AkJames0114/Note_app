package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityBooknoteDetailsBinding;
import com.example.noteapp.model.BookNote;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookNoteDetailsActivity extends BaseActivity<ActivityBooknoteDetailsBinding> {

    private BookNote bookNote;

    @Override
    protected ActivityBooknoteDetailsBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityBooknoteDetailsBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.book_note_details_title);
       /*
        int id = getIntent().getIntExtra("id", 0);
        *if (id != 0) {
         *   Note note = dataBaseHelper.getNote(id);
          *  binding.title.setText(note.getTitle());
           * binding.content.setText(note.getContent());
        }
        */
        bookNote = (BookNote) getIntent().getSerializableExtra("book_note");

        }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (bookNote!=null){
//            bookNote=dataBaseHelper.getBookNote(bookNote.getId());
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.book_note_details_menu,  menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bookNote != null) {
            binding.title.setText(bookNote.getTitle());
            binding.content.setText(bookNote.getDescription());

            RequestOptions options = new RequestOptions();
            options.centerCrop();

            Glide.with(binding.getRoot()).load(bookNote.getImage()).apply(options).placeholder(R.drawable.ic_image).into(binding.imageBookNoteDetails);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.editBookNote) {

            Intent intent = new Intent(BookNoteDetailsActivity.this, AddBookNoteActivity.class);

             intent.putExtra("book_note", bookNote);

            startActivity(intent);

            return true;
        } else if (item.getItemId() == R.id.deleteBtn) {
            deleteBookNote();
            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }
    }
    private void deleteBookNote(){
        if (bookNote != null){

            String access_token = (String) preferenceManager.getValue(String.class, "access_token", "");
            String bearer_token = "Bearer " + access_token;
            Call<JsonObject> call = mainApi.deleteBookNote(bearer_token, bookNote.getId());
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful()){
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });
        }
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
