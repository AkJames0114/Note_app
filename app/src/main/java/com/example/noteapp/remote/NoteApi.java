package com.example.noteapp.remote;

import com.example.noteapp.model.Note;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NoteApi {

    //ArrayList<Note> getNotes()

    //Base URL=http://api-sw.beknumonov.com/
    // v1/notes/
    @GET("v1/notes/")
    Call<ArrayList<JsonObject>> getNotes();

    @POST("v1/notes/")
    Call<Note> createNote(@Body Note note);

}
