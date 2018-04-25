package com.example.nguyen.clickergame

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.view.Gravity
import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.TextView



class ColoursTaps : AppCompatActivity() {

    private var gridLayout: GridLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colours_taps)

        gridLayout = findViewById(R.id.grid_colours) as GridLayout
    }

    fun makeButtons() {
        gridLayout!!.setRowCount(2)
        gridLayout!!.setColumnCount(2)
        var param: GridLayout.LayoutParams
        var i = 0
        var c = 0
        var r = 0
        while (i < 4) {
            if (c == 2) {
                c = 0
                r++
            }
            val button = TextView(this)
            button.setTextColor(Color.WHITE)
            val s = "" + (i + 1)
            button.text = s
            button.id = i
            button.gravity = Gravity.CENTER
            val button_animation = button.background as AnimationDrawable
            button.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    //Toast.makeText(getBaseContext(), button.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            })
            param = GridLayout.LayoutParams()
            param.height = 300
            param.width = 300
            param.rightMargin = 125
            param.leftMargin = 125
            param.topMargin = 30
            param.bottomMargin = 30
            param.setGravity(Gravity.CENTER)
            param.columnSpec = GridLayout.spec(c)
            param.rowSpec = GridLayout.spec(r)
            gridLayout.addView(button, param)
            i++
            c++
        }//Finished adding simon buttons
    }
}
