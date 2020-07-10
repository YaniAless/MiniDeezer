package com.example.minideezer


import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout

class CustomView(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    private var play_pause: Button
    private var previous: Button
    private var next: Button

    init {
        View.inflate(context, R.layout.custom_view, this)

        play_pause = findViewById(R.id.play_pause)
        previous = findViewById(R.id.previous)
        next = findViewById(R.id.next)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomView,
            0, 0).apply {
            play_pause.setBackgroundResource(getResourceId(R.styleable.CustomView_src, R.drawable.play_pause))
        }.recycle()

    }
}