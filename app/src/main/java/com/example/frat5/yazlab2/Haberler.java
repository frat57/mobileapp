package com.example.frat5.yazlab2;

public class Haberler {
    private int id,like_number,dislike_number,view_count;

    private String name,date;

    public Haberler(){

    }

    public Haberler(int id, int like_number, int dislike_number, int view_count, String name, String content, String type, String image_link, String date) {
        this.id = id;
        this.like_number = like_number;
        this.dislike_number = dislike_number;
        this.view_count = view_count;
        this.name = name;
        this.content = content;
        this.type = type;
        this.image_link = image_link;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLike_number() {
        return like_number;
    }

    public void setLike_number(int like_number) {
        this.like_number = like_number;
    }

    public int getDislike_number() {
        return dislike_number;
    }

    public void setDislike_number(int disslike_number) {
        this.dislike_number = disslike_number;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    private String content;
    private String type;
    private String image_link;


}
