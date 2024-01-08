package com.example.noteapp.remote;

import com.example.noteapp.model.News;
import com.example.noteapp.model.Note;
import com.example.noteapp.model.Token;
import com.example.noteapp.model.User;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MainApi {

    //Base URL=http://api.note-app.beknumonov.com

    //User endpoint: v1/user/

    @POST("v1/user/")
    Call<User> createUser(@Body User user);

    @POST("v1/auth-token/")
    Call<Token> login (@Body User user);

    // endpoint: v1/notes/
    @GET("v1/notes/")
    Call<ArrayList<JsonObject>> getNotes();

    @POST("v1/notes/")
    Call<Note> createNote(@Body Note note);

}
