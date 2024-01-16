package com.example.noteapp;

import android.Manifest;
import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.noteapp.base.BaseActivity;
import com.example.noteapp.databinding.ActivityAddBookNoteBinding;
import com.example.noteapp.model.BookNote;
import com.example.noteapp.model.Note;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBookNoteActivity extends BaseActivity<ActivityAddBookNoteBinding> {

    private BookNote bookNote;
    private File selectedImageFile;


    @Override
    protected ActivityAddBookNoteBinding inflateViewBinding(LayoutInflater inflater) {
        return ActivityAddBookNoteBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.add_new_book_note_activity_title);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},2000);
        }

        if (getIntent().hasExtra("book_note")) {
            bookNote = (BookNote) getIntent().getSerializableExtra("book_note");
            binding.createBtn.setText("Update");
            setTitle("Edit Book_Note");
            binding.title.setText(bookNote.getTitle());
            binding.content.setText(bookNote.getDescription());

            RequestOptions options = new RequestOptions();
            options.centerCrop();

            Glide.with(binding.getRoot()).load(bookNote.getImage()).apply(options).placeholder(R.drawable.ic_image).into(binding.loadImageBtn);
        }
        binding.loadImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
              // intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "SELECT PHOTOS"), 1000);

            }
        });

        binding.createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = binding.title.getText().toString();
                String description = binding.content.getText().toString();



                if (bookNote == null) {

                    bookNote = new BookNote();
                    bookNote.setTitle(title);
                    bookNote.setDescription(description);
                    //dataBaseHelper.addBookNote(booknote);

                    if (selectedImageFile != null) {

                        RequestBody titleRB = RequestBody.create(MultipartBody.FORM, title);
                        RequestBody descriptionRB = RequestBody.create(MultipartBody.FORM, title);
                        String mediaType = "jpg";
                        RequestBody imageRB = RequestBody.create(MediaType.parse(mediaType), selectedImageFile);

                        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", selectedImageFile.getName(), imageRB);


                        String access_token = (String) preferenceManager.getValue(String.class, "access_token", "");
                        String bearer_token = "Bearer " + access_token;

                        Call<BookNote> call = mainApi.createBookNote(bearer_token, titleRB, descriptionRB, imagePart);
                        call.enqueue(new Callback<BookNote>() {
                            @Override
                            public void onResponse(Call<BookNote> call, Response<BookNote> response) {
                                Log.d("BookNote: ", response.body().toString() + "is created");
                                finish();
                            }

                            @Override
                            public void onFailure(Call<BookNote> call, Throwable t) {
                                Log.d("Failed: ", t.getMessage() + "is created");
                            }
                        });
                    }
                } else {
                    bookNote.setTitle(title);
                    bookNote.setDescription(bookNote.getDescription());
                    dataBaseHelper.updateBookNote(bookNote);
                    finish();
                }
            }
        });
    }

    public String getPathFromUri(Uri contentUri) {
        String path = null;

        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
//        CursorLoader cursorLoader=new CursorLoader(this, contentUri, proj, null,null,null);
//        Cursor cursor = cursorLoader.loadInBackground();
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            path = cursor.getString(column_index);

        }
        cursor.close();
        return path;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            try {
                Uri selectedImageUri = data.getData();
                binding.loadImageBtn.setImageURI(selectedImageUri);
                String path = getPathFromUri(selectedImageUri);
                selectedImageFile = new File(path);

                Log.d("Path", path);
            } catch (Exception e) {

            }
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
