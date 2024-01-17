package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityNoteDetailsBinding;
import com.example.noteapp.model.Note;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoteDetailsActivity extends BaseActivity<ActivityNoteDetailsBinding> {

    private Note note;

    @Override
    protected ActivityNoteDetailsBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityNoteDetailsBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.note_details_title);
       /*
        int id = getIntent().getIntExtra("id", 0);
        *if (id != 0) {
         *   Note note = dataBaseHelper.getNote(id);
          *  binding.title.setText(note.getTitle());
           * binding.content.setText(note.getContent());
        }
        */
        note = (Note) getIntent().getSerializableExtra("note");


        }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (note!=null){
//            note=dataBaseHelper.getNote(note.getId());
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_details_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (note != null) {
            binding.title.setText(note.getTitle());
            binding.content.setText(note.getContent());
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.editNote) {

            Intent intent = new Intent(NoteDetailsActivity.this, AddNoteActivity.class);

             intent.putExtra("note", note);

            startActivity(intent);

            return true;

        } else if (item.getItemId() == R.id.deleteBtn) {
            deleteNote();
            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }
    }
    private void deleteNote() {
        if (note != null){

            String access_token = (String) preferenceManager.getValue(String.class, "access_token", "");
            String bearer_token = "Bearer " + access_token;
            Call<JsonObject> call = mainApi.deleteNote(bearer_token, note.getId());
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
