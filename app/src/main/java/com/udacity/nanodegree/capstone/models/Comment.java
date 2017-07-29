package com.udacity.nanodegree.capstone.models;

import android.content.Context;

import com.google.firebase.database.IgnoreExtraProperties;
import com.udacity.nanodegree.capstone.R;


// [START comment_class]
@IgnoreExtraProperties
public class Comment {

    public static final String BLANK_SPACE = " ";


    public String uid;
    public String author;
    public String text;

    StringBuilder commentText;

    public Comment() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public Comment(String uid, String author) {
        this.uid = uid;
        this.author = author;
        commentText = new StringBuilder();
    }

    public void setCommentFrom(Context context, String from) {
        commentText.append(context.getString(R.string.comment_from) + BLANK_SPACE +  from + BLANK_SPACE);

    }

    public void setCommentTo(Context context, String to) {
        commentText.append(context.getString(R.string.comment_to) + BLANK_SPACE + to + BLANK_SPACE);

    }

    public void setTransportationOption(Context context, String transportationOption) {
        commentText.append(context.getString(R.string.comment_transportation_option)
                + BLANK_SPACE +transportationOption + BLANK_SPACE);
    }


    public void setEstimateTime(Context context, String time) {
        commentText.append(context.getString(R.string.comment_estimate_time) + BLANK_SPACE + time + BLANK_SPACE);

    }

    public void setAdditionalInfo(Context context, String additionalInfo) {
        commentText.append(context.getString(R.string.comment_additional_info) + BLANK_SPACE
                +additionalInfo + BLANK_SPACE);
    }


    public void makeCommentText() {
        this.text = commentText.toString();
    }

}
// [END comment_class]
