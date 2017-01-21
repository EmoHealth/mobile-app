package com.example.marx.emohealth.post;

import java.util.Calendar;

public class Post {

    private String mood;
    private String comments;
    private Calendar timeOfPost;

    public Post(String mood, String comments, Calendar timeOfPost){
        this.mood = mood;
        this.comments = comments;
        this.timeOfPost = timeOfPost;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setComments(String comments){
        this.comments = comments;
    }

    public String getMood(){
        return this.mood;
    }

    public String getComments(){
        return this.comments;
    }

    public Calendar getTimeOfPost(){
        return this.timeOfPost;
    }

}