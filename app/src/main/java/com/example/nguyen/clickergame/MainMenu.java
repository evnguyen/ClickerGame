package com.example.nguyen.clickergame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

public class MainMenu extends AppCompatActivity {

    private RelativeLayout tapsLayout;
    private Animation compileAnim;
    private AdView adView;    //Used for Ads
    private ImageView classicBtn;
    private ImageView settingsBtn;
    private TextView titleLeft;
    private TextView titleMiddle;
    private TextView titleRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        tapsLayout = (RelativeLayout) findViewById(R.id.taps_layout);

        //Hide action bars on fullscreen
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        initClassic();
        initSetting();
        initTitle();


    }

    public void initClassic(){
        classicBtn = (ImageView) findViewById(R.id.classic);
        compileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_classic_button);
        compileAnim.setDuration(GameSettings.ANIMATION_OPEN__BUTTON_DURATION);
        compileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                classicBtn.setImageResource(R.mipmap.classic);
                classicBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentClassic = new Intent(MainMenu.this, ClassicTaps.class);
                        intentClassic.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intentClassic);
                    }
                });

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        classicBtn.startAnimation(compileAnim);
    }


    private void initTitle(){
        titleLeft = (TextView)findViewById(R.id.taps_left);
        titleMiddle = (TextView)findViewById(R.id.taps_middle);
        titleRight = (TextView)findViewById(R.id.taps_right);

        compileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.back_anim_for_title_left);
        compileAnim.setDuration(GameSettings.ANIMATION_HIDE_TITLE_DURATION);
        compileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

         titleLeft.startAnimation(compileAnim);
        //End title left animation

        compileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.back_anim_title_middle);
        compileAnim.setDuration(GameSettings.ANIMATION_HIDE_TITLE_DURATION);
        compileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        titleMiddle.startAnimation(compileAnim);
        //End title middle animation

        compileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.back_anim_title_right);
        compileAnim.setDuration(GameSettings.ANIMATION_HIDE_TITLE_DURATION);
        compileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        titleRight.startAnimation(compileAnim);
        //End title right animation
    }




    private void initSetting(){
        settingsBtn = (ImageView) findViewById(R.id.settings);
        compileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_settings_button);
        compileAnim.setDuration(GameSettings.ANIMATION_OPEN__BUTTON_DURATION);
        compileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                settingsBtn.setImageResource(R.mipmap.settings);
                settingsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        settingsBtn.setImageResource(R.mipmap.menu_options);
                        classicBtn.setImageResource(R.mipmap.menu_options);

                        Animation animReverseClassic = AnimationUtils.loadAnimation(MainMenu.this, R.anim.reverse_for_classic_button);
                        animReverseClassic.setDuration(GameSettings.ANIMATION_CLOSE__BUTTON_DURATION);

                        Animation animaReverseSettings = AnimationUtils.loadAnimation(MainMenu.this, R.anim.reverse_for_settings_button);
                        animaReverseSettings.setDuration(GameSettings.ANIMATION_CLOSE__BUTTON_DURATION);

                        Animation animationTitleLeft = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_title_left);
                        animationTitleLeft.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);

                        Animation animationTitleMiddle = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_title_middle);
                        animationTitleMiddle.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);

                        Animation animationTitleRight = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_title_right);
                        animationTitleRight.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);

                                classicBtn.startAnimation(animReverseClassic);
                                settingsBtn.startAnimation(animaReverseSettings);
                                titleLeft.startAnimation(animationTitleLeft);
                                titleMiddle.startAnimation(animationTitleMiddle);
                                titleRight.startAnimation(animationTitleRight);

                                //Delay going into settings menu until after animations are complete
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                Intent settingsIntent = new Intent(MainMenu.this, Settings.class);
                                settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(settingsIntent);
                            }
                        }, GameSettings.ANIMATION_START_ACTIVITY_DURATION);

                    }
                    //End onClick
                });//End onClickListener
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        settingsBtn.setAnimation(compileAnim);


    }


}
