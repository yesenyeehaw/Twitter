package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;

import org.parceler.Parcels;

public class TweetDetails extends AppCompatActivity {
    ImageView ivProfileImg;
    TextView tvName;
    TextView tvUser;
    TextView tvBody;
    TextView tvLike;
    TextView tvRetweet;
    TwitterClient client;
    ImageView ivTweetMedia;

    //To get the current tweet
    Tweet currentTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        // A "Client" is basically the person using the API
        client = TwitterApp.getRestClient(this);

        ivProfileImg = findViewById(R.id.ivProfileImg);
        tvName = findViewById(R.id.tvName);
        tvUser = findViewById(R.id.tvUser);
        tvBody = findViewById(R.id.tvBody);
        tvLike = findViewById(R.id.tvLike);
        tvRetweet = findViewById(R.id.tvRetweet);
        ivTweetMedia = findViewById(R.id.ivTweetMedia);

        currentTweet = Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getName()));

        tvName.setText(currentTweet.user.name);
        tvUser.setText("@" + currentTweet.user.screenName);
        tvBody.setText(currentTweet.body);
        tvLike.setText(String.valueOf(currentTweet.likeCt));
        tvRetweet.setText(String.valueOf(currentTweet.retweetCt));

        Glide.with(this).load(currentTweet.user.profileImageUrl).into(ivProfileImg);

        //if there is an image
        if(! currentTweet.image.isEmpty()){
            //display the image
            Glide.with(this).load(currentTweet.image).into(ivTweetMedia);
        }
        else{
            ivTweetMedia.setImageDrawable(null);
        }
    }
}