package com.example.nguyen.clickergame

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.MotionEventCompat
import android.support.v7.app.AppCompatActivity
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import com.example.nguyen.clickergame.R.id.taps

class ClassicTaps : AppCompatActivity() {

    private var playMusic: Boolean = false
    private var musicPlayer: MediaPlayer? = null
    private var classicLayout: RelativeLayout? = null
    private var isInitialized: Boolean = false
    private val gestureDetector: GestureDetector? = null
    private var isPaused: Boolean = false
    private val myHandler: Handler? = null
    private val head: ImageView? = null
    private var taps_text: TextView? = null
    private var tps_text: TextView? = null
    private var numtaps: Int = 0
    private val button: Button? = null
    private val tps: Int = 0
    private var taps_start: Int = 0
    private var taps_end: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classic_taps)

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        //Hide action bars on fullscreen
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        decorView.systemUiVisibility = uiOptions
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        musicOnOff()

        classicLayout = findViewById(R.id.content_classic_layout) as RelativeLayout
        classicLayout!!.setBackgroundResource(R.mipmap.background_for_snake)
        classicLayout!!.setPaddingRelative(GameSettings.LAYOUT_PADDING, GameSettings.LAYOUT_PADDING, GameSettings.LAYOUT_PADDING, GameSettings.LAYOUT_PADDING)

        numtaps = 0
        taps_text = findViewById(taps) as TextView
        tps_text = findViewById(R.id.tps) as TextView


        //TODO: Fix the warning.
        classicLayout!!.setOnTouchListener({view, motionEvent ->
            when (MotionEventCompat.getActionMasked(motionEvent)) {
                MotionEvent.ACTION_POINTER_DOWN -> {
                    numtaps++
                    taps_text!!.text = "Total Taps: " + numtaps
                    println("action pointer down")
                }
                MotionEvent.ACTION_DOWN -> {
                    numtaps++
                    taps_text!!.text = "Total Taps: " + numtaps
                }
            }
            return@setOnTouchListener true;
        })

        isInitialized = false

        updateTPS()

    }

    private fun musicOnOff() {
        val preferences = applicationContext.getSharedPreferences("ClickerPreferences", Context.MODE_PRIVATE)
        playMusic = preferences.getBoolean("PlayMusic", true)
        musicPlayer = MediaPlayer.create(this@ClassicTaps, R.raw.music)
        if (playMusic) {
            musicPlayer!!.isLooping = true
            musicPlayer!!.start()
        } else {
            musicPlayer!!.stop()
        }
    }


    override fun onPause() {
        super.onPause()
        isPaused = true
        musicPlayer!!.release()
    }


    private fun updateTPS() {
        val mHandler = Handler()
        Thread(Runnable {
            while (true) {
                try {
                    taps_start = numtaps
                    Thread.sleep(1000)
                    taps_end = numtaps - taps_start
                    mHandler.post { tps_text!!.text = "TPS: $taps_end/second" }
                } catch (e: Exception) {
                    //TODO
                }

            }
        }).start()
    }


}
