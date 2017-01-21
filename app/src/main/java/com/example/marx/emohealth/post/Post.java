package com.example.marx.emohealth.post;

import java.io.Serializable;
import java.util.Calendar;

public class Post implements Serializable{

    private Integer mood;
    private String comments;
    private Calendar timeOfPost;

    public Post(Integer mood, String comments, Calendar timeOfPost){
        this.mood = mood;
        this.comments = comments;
        this.timeOfPost = timeOfPost;
    }

    public void setMood(Integer mood) {
        this.mood = mood;
    }

    public void setComments(String comments){
        this.comments = comments;
    }

    public Integer getMood(){
        return this.mood;
    }

    public String getComments(){
        return this.comments;
    }

    public Calendar getTimeOfPost(){
        return this.timeOfPost;
    }

}
