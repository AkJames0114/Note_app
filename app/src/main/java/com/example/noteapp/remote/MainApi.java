package com.example.noteapp.remote;

import com.example.noteapp.model.BookNote;
import com.example.noteapp.model.News;
import com.example.noteapp.model.Note;
import com.example.noteapp.model.Token;
import com.example.noteapp.model.User;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface MainApi {

    //Base URL=http://api.note-app.beknumonov.com

    //User endpoint: v1/user/

    @POST("v1/user/")
    Call<User> createUser(@Body User user);

    @POST("v1/auth-token/")
    Call<Token> login(@Body User user);

    // endpoint: v1/notes/
    @GET("v1/note/")
    Call<ArrayList<Note>> getNotes(@Header("Authorization") String accessToken);
    @GET("v1/book/")
    Call<ArrayList<BookNote>> getBookNotes(@Header("Authorization") String accessToken);

    @POST("v1/note/")
    Call<Note> createNote(@Header("Authorization") String accessToken, @Body Note note);

    @Multipart
    @POST("v1/book/")
    Call<BookNote> createBookNote(@Header("Authorization") String accessToken,
                                  @Part("title") RequestBody title,
                                  @Part("description") RequestBody description,
                                  @Part MultipartBody.Part image
    );

    @GET("v1/news/")
    Call<ArrayList<News>> getNews(@Header("Authorization") String accessToken);

    @DELETE("v1/book/{id}/")
    Call<JsonObject> deleteBookNote(@Header("Authorization") String accessToken, @Path("id") int id); // Deleting data by id and access token is used for Authorization.

    @DELETE("v1/note/{id}/")
    Call<JsonObject> deleteNote(@Header("Authorization") String accessToken, @Path("id") int id); // Deleting data by id and access token is used for Authorization.
}
