package com.example.noteapp.model;

import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String title;

    @SerializedName("source")
    private String source;

    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String content;

    @SerializedName("image_url")
    private String image_url;

    @SerializedName("publishedAt")
    private String publishedAt;

    public News(Integer id, String title, String source, String author, String content, String image_url, String publishedAt) {
        this.id = id;
        this.title = title;
        this.source = source;
        this.author = author;
        this.content = content;
        this.image_url = image_url;
        this.publishedAt = publishedAt;
    }

    public News(){

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", image_url='" + image_url + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                '}';
    }
}
