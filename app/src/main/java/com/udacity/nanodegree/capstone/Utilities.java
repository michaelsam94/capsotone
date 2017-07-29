package com.udacity.nanodegree.capstone;


import android.content.Context;


import com.udacity.nanodegree.capstone.models.Comment;
import com.udacity.nanodegree.capstone.models.Post;

public class Utilities {

    private static Utilities instance;

    private Utilities(){

    }

    public static Utilities getInstance(){
        if(instance == null) instance = new Utilities();
        return instance;
    }

    public boolean validatePost(Post post){
        if(post != null){
            if(post.body != null) return true;
        }
        return false;
    }

    public boolean validateComment(Context context, Comment comment){
        boolean isValid = true;
        if(comment != null){
            if(!comment.text.contains(context.getString(R.string.comment_to))) isValid = false;
            if(!comment.text.contains(context.getString(R.string.comment_from))) isValid = false;
            if(!comment.text.contains(context.getString(R.string.comment_transportation_option))) isValid = false;
        }
        return isValid;
    }








}
