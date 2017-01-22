package com.example.marx.emohealth.com.example.marx.emohealth.data;


import android.content.Context;
import android.util.Log;

import com.example.marx.emohealth.post.Post;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataStorage {

    public static String userFileToSave = "all-posts";

    public static ArrayList<Post> readData(Context c){
        try {
            FileInputStream fis = c.openFileInput(userFileToSave);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Post> listOfPosts = (ArrayList<Post>) ois.readObject();
            ois.close();
            Log.i("Info", "File has successfully been saved");
            return listOfPosts;

        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return new ArrayList<Post>();
    }

    public static void saveData(Context c, Post postToAdd){
        ArrayList<Post> listToSave = readData(c);
        listToSave.add(postToAdd);

        try {
            FileOutputStream fos = c.openFileOutput(userFileToSave, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listToSave);
            oos.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
