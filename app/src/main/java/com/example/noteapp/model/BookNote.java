package com.example.noteapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BookNote implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("image")
    private String image;
    @SerializedName("page_count")
    private String pageCount;
    @SerializedName("created_at")
    private String createdAt;

    public BookNote(Integer id, String title, String description, String image, String pageCount, String createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.pageCount = pageCount;
        this.createdAt = createdAt;
    }

    public BookNote(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;


    }

    public BookNote() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "BookNote{" +
                ", title='" + title + '\'' +
                ", content='" + description + '\'' +
                '}';
    }

    public static class BookNoteEntry {
        public static String TABLE_NAME = "BOOK_NOTES";
        public static String ID = "ID";
        public static String TITLE = "TITLE";
        public static String CONTENT = "CONTENT";
        public static String CREATED_AT = "CREATED_AT";

        /*
        CREATE TABLE TABLE_NAME(
        ID INTEGER PRIMARY KEY AUTOINCREMENT,
        TITLE TEXT NOT NULL,
        CONTENT TEXT NOT NULL,
        CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP)
        */
        public static String createTableQuery() {
            String sql = "CREATE TABLE " + TABLE_NAME + " ( " +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TITLE + " TITLE TEXT NOT NULL," +
                    CONTENT + " CONTENT TEXT NOT NULL," +
                    CREATED_AT + " CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ");";
            return sql;


        }


    }
}
