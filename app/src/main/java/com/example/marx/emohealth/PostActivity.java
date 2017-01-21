package com.example.marx.emohealth;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.marx.emohealth.post.Post;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class PostActivity extends AppCompatActivity implements Serializable{

    final String MOOD_SAD = "mood-sad";
    final String MOOD_NEUTRAL = "mood-neutral";
    final String MOOD_HAPPY = "mood-happy";

    private Integer images[] = {R.drawable.sad, R.drawable.neutral, R.drawable.happy};
    private Integer currImage = 0;

    private String currentMood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        addImagesToGallery();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    protected void addImagesToGallery(){
        LinearLayout imageGallery = (LinearLayout) findViewById(R.id.imageGallery);
        for (Integer image : images){
            imageGallery.addView(getImageView(image));
        }
    }

    protected View getImageView(Integer image){
        ImageView imageView = new ImageView(getApplicationContext());
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 10, 0);
        imageView.setLayoutParams(lp);
        imageView.setImageResource(image);
        return imageView;
    }


    /**
     * The user has decided to post his mood.
     * The method will first save all the details of his current mood into the Internal Storage.
     * Then it will call the intent into the overview activity.
     */
    protected void postMood(){

        Calendar currentTime = Calendar.getInstance();

        EditText commentMessage = (EditText) findViewById(R.id.comment_line);
        String comment = commentMessage.getText().toString();

        // The user has yet to select a mood. Prompt error pop up
        if (currentMood == null){

        }
        Post postToSave = new Post(currentMood, comment, currentTime);
        saveData(postToSave);
    }

    protected void saveData(Post postToSave){

        try {
            FileOutputStream fos = openFileOutput(MainActivity.userFileToSave, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(postToSave);
            oos.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void selectMoodSad(){
        currentMood = MOOD_SAD;
    }

    private void selectMoodHappy(){
        currentMood = MOOD_HAPPY;
    }

    private void selectMoodNeutral(){
        currentMood = MOOD_NEUTRAL;
    }

}
