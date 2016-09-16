package com.iamdeveloper.youtubeplay;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;


public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    public static final String API_KEY = "AIzaSyAW3S1DKdSrvn-kl3J3ROCqYzYfPPybD60";
    private CoordinatorLayout coordinatorLayout;
    private String appPackageName = "com.google.android.youtube";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo("rlmRaDatQEM");
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Snackbar.make(coordinatorLayout,"คุณต้องทำการติดตั้ง youtube ก่อน",Snackbar.LENGTH_INDEFINITE)
                .setAction("download", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse("market://detail?id="+appPackageName));
                            startActivity(i);
                        }catch(Exception e){
                            Log.d("exception",e.toString());
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse("https://play.google.com/store/apps/details?id="+appPackageName));
                            startActivity(i);
                        }
                    }
                }).show();
    }
}
