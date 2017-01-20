package com.example.nguyen.clickergame;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.example.nguyen.clickergame.R.id.taps;

public class ClassicTaps extends AppCompatActivity {

    private boolean playMusic;
    private MediaPlayer musicPlayer;
    private RelativeLayout classicLayout;
    private boolean isInitialized;
    private GestureDetector gestureDetector;
    private boolean isPaused;
    private Handler myHandler;
    private ImageView head;
    private TextView taps_text;
    private TextView tps_text;
    private int numtaps;
    private Button button;
    private int tps, taps_start, taps_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_classic_taps);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        //Hide action bars on fullscreen
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        musicOnOff();

        classicLayout = (RelativeLayout) findViewById(R.id.content_classic_layout);
        classicLayout.setBackgroundResource(R.mipmap.background_for_snake);
        classicLayout.setPaddingRelative(GameSettings.LAYOUT_PADDING,GameSettings.LAYOUT_PADDING,GameSettings.LAYOUT_PADDING,GameSettings.LAYOUT_PADDING);

        numtaps = 0;
        taps_text = (TextView) findViewById(taps);
        tps_text = (TextView) findViewById(R.id.tps);

        classicLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                numtaps++;
                taps_text.setText("Total Taps: " + numtaps);
            }
        });

        isInitialized = false;

        updateTPS();

    }

    private void musicOnOff(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("ClickerPreferences", Context.MODE_PRIVATE);
        playMusic = preferences.getBoolean("PlayMusic", true);
        musicPlayer = MediaPlayer.create(ClassicTaps.this, R.raw.music);
        if(playMusic){
            musicPlayer.setLooping(true);
            musicPlayer.start();
        }
        else{
            musicPlayer.stop();
    }
}


    @Override
    protected void onPause(){
        super.onPause();
        isPaused = true;
        musicPlayer.release();
    }


    private void updateTPS(){
        final Handler mHandler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        taps_start = numtaps;
                        Thread.sleep(1000);
                        taps_end = numtaps - taps_start;
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                tps_text.setText("TPS: " + taps_end + "/second");
                            }
                        });
                    } catch (Exception e) {
                        //TODO
                    }
                }
            }
        }).start();
    }




}
