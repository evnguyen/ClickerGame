package com.example.nguyen.clickergame

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import com.google.android.gms.ads.AdView

class MainMenu : AppCompatActivity() {

    private var tapsLayout: RelativeLayout? = null
    private var compileAnim: Animation? = null
    private val adView: AdView? = null    //Used for Ads
    private var classicBtn: ImageView? = null
    private var settingsBtn: ImageView? = null
    private var titleLeft: TextView? = null
    private var titleMiddle: TextView? = null
    private var titleRight: TextView? = null
    private var coloursTaps: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)
        tapsLayout = findViewById(R.id.taps_layout) as RelativeLayout

        //Hide action bars on fullscreen
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
        decorView.systemUiVisibility = uiOptions
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        initClassic()
        initColours()
        initSetting()
        initTitle()
    }

    private fun initClassic() {
        classicBtn = findViewById(R.id.classic) as ImageView
        compileAnim = AnimationUtils.loadAnimation(this@MainMenu, R.anim.anim_for_classic_button)
        compileAnim!!.duration = GameSettings.ANIMATION_OPEN__BUTTON_DURATION.toLong()
        compileAnim!!.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                classicBtn!!.setImageResource(R.mipmap.classic)
                classicBtn!!.setOnClickListener {
                    val intentClassic = Intent(this@MainMenu, ClassicTaps::class.java)
                    intentClassic.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intentClassic)
                }

            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })

        classicBtn!!.startAnimation(compileAnim)
    }

    private fun initColours() {
        val intent = Intent(this@MainMenu, ColoursTaps::class.java)
        startActivity(intent)
    }


    private fun initTitle() {
        titleLeft = findViewById(R.id.taps_left) as TextView
        titleMiddle = findViewById(R.id.taps_middle) as TextView
        titleRight = findViewById(R.id.taps_right) as TextView

        compileAnim = AnimationUtils.loadAnimation(this@MainMenu, R.anim.back_anim_for_title_left)
        compileAnim!!.duration = GameSettings.ANIMATION_HIDE_TITLE_DURATION.toLong()
        compileAnim!!.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {

            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })

        titleLeft!!.startAnimation(compileAnim)
        //End title left animation

        compileAnim = AnimationUtils.loadAnimation(this@MainMenu, R.anim.back_anim_title_middle)
        compileAnim!!.duration = GameSettings.ANIMATION_HIDE_TITLE_DURATION.toLong()
        compileAnim!!.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {

            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })

        titleMiddle!!.startAnimation(compileAnim)
        //End title middle animation

        compileAnim = AnimationUtils.loadAnimation(this@MainMenu, R.anim.back_anim_title_right)
        compileAnim!!.duration = GameSettings.ANIMATION_HIDE_TITLE_DURATION.toLong()
        compileAnim!!.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {

            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })

        titleRight!!.startAnimation(compileAnim)
        //End title right animation
    }


    private fun initSetting() {
        settingsBtn = findViewById(R.id.settings) as ImageView
        compileAnim = AnimationUtils.loadAnimation(this@MainMenu, R.anim.anim_for_settings_button)
        compileAnim!!.duration = GameSettings.ANIMATION_OPEN__BUTTON_DURATION.toLong()
        compileAnim!!.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                settingsBtn!!.setImageResource(R.mipmap.settings)
                settingsBtn!!.setOnClickListener {
                    window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    settingsBtn!!.setImageResource(R.mipmap.menu_options)
                    classicBtn!!.setImageResource(R.mipmap.menu_options)

                    val animReverseClassic = AnimationUtils.loadAnimation(this@MainMenu, R.anim.reverse_for_classic_button)
                    animReverseClassic.duration = GameSettings.ANIMATION_CLOSE__BUTTON_DURATION.toLong()

                    val animaReverseSettings = AnimationUtils.loadAnimation(this@MainMenu, R.anim.reverse_for_settings_button)
                    animaReverseSettings.duration = GameSettings.ANIMATION_CLOSE__BUTTON_DURATION.toLong()

                    val animationTitleLeft = AnimationUtils.loadAnimation(this@MainMenu, R.anim.anim_for_title_left)
                    animationTitleLeft.duration = GameSettings.ANIMATION_SHOW_TITLE_DURATION.toLong()

                    val animationTitleMiddle = AnimationUtils.loadAnimation(this@MainMenu, R.anim.anim_for_title_middle)
                    animationTitleMiddle.duration = GameSettings.ANIMATION_SHOW_TITLE_DURATION.toLong()

                    val animationTitleRight = AnimationUtils.loadAnimation(this@MainMenu, R.anim.anim_for_title_right)
                    animationTitleRight.duration = GameSettings.ANIMATION_SHOW_TITLE_DURATION.toLong()

                    classicBtn!!.startAnimation(animReverseClassic)
                    settingsBtn!!.startAnimation(animaReverseSettings)
                    titleLeft!!.startAnimation(animationTitleLeft)
                    titleMiddle!!.startAnimation(animationTitleMiddle)
                    titleRight!!.startAnimation(animationTitleRight)

                    //Delay going into settings menu until after animations are complete
                    val handler = Handler()
                    handler.postDelayed({
                        val settingsIntent = Intent(this@MainMenu, Settings::class.java)
                        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(settingsIntent)
                    }, GameSettings.ANIMATION_START_ACTIVITY_DURATION.toLong())
                }
                    //End onClick
//End onClickListener
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        settingsBtn!!.animation = compileAnim


    }


}
