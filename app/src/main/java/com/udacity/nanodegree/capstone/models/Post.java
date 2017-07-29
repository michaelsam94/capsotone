package com.udacity.nanodegree.capstone.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START post_class]
@IgnoreExtraProperties
public class Post {

    public String uid;
    public String author;
    public String title;
    public String body;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Post(String uid, String author, String from, String to) {
        this.uid = uid;
        this.author = author;
        if(from != null && to != null){
            this.title = makeTitle(from,to);
            this.body = makeBody(from,to);
        }

    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("title", title);
        result.put("body", body);
        result.put("starCount", starCount);
        result.put("stars", stars);

        return result;
    }
    // [END post_to_map]

    private String makeTitle(String from, String to){
        return from + ", " + to;
    }

    private String makeBody(String from, String to){
        return "What is transportation options available " + from + " " + "to " + to + " thanks?";
    }

}
// [END post_class]
