package com.udacity.nanodegree.capstone;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.udacity.nanodegree.capstone.models.Comment;
import com.udacity.nanodegree.capstone.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewCommentActivity extends BaseActivity {


    public static final String EXTRA_POST_KEY = "post_key";


    @BindView(R.id.spnr_comment_transportaion_option)
    Spinner spnrTransportaionOption;
    @BindView(R.id.et_comment_from)
    EditText etCommentFrom;
    @BindView(R.id.et_comment_to)
    EditText etCommentTo;
    @BindView(R.id.et_comment_estimate_time)
    EditText etCommentEstimateTime;
    @BindView(R.id.et_comment_additional_info)
    EditText etCommentAdditionalInfo;


    private DatabaseReference mCommentsReference;
    private String mPostKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comment);
        ButterKnife.bind(this);

        // Get post key from intent
        mPostKey = getIntent().getStringExtra(EXTRA_POST_KEY);
        if (mPostKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_POST_KEY");
        }

        mCommentsReference = FirebaseDatabase.getInstance().getReference()
                .child("post-comments").child(mPostKey);

    }

    @OnClick(R.id.fab_submit_comment)
    public void onSubmitComment(){
        postComment();
    }

    private void postComment() {
        final String uid = getUid();
        FirebaseDatabase.getInstance().getReference().child("users").child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user information
                        User user = dataSnapshot.getValue(User.class);
                        String authorName = user.username;

                        // Create new comment object

                        Comment comment = new Comment(uid, authorName);
                        fillCommentFromFields(comment);
                        // Push the comment, it will appear in the list
                        if(Utilities.getInstance().validateComment(NewCommentActivity.this,comment)){
                            mCommentsReference.push().setValue(comment);
                            finish();
                        } else {
                            Toast.makeText(NewCommentActivity.this,"please fill all fields",Toast.LENGTH_SHORT);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private void fillCommentFromFields(Comment comment){
        comment.setTransportationOption(this,spnrTransportaionOption.getSelectedItem().toString());
        comment.setCommentFrom(this,etCommentFrom.getText().toString());
        comment.setCommentTo(this,etCommentTo.getText().toString());
        comment.setAdditionalInfo(this,etCommentAdditionalInfo.getText().toString());
        comment.setEstimateTime(this,etCommentEstimateTime.getText().toString());
        comment.makeCommentText();
    }


}
