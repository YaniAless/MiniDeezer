package com.example.minideezer


import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

class MusicPlayerCustomView(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    private var play_pause: Button
    private var previous: Button
    private var next: Button

    init {
        View.inflate(context, R.layout.music_player, this)

        play_pause = findViewById(R.id.play_pause)
        previous = findViewById(R.id.previous)
        next = findViewById(R.id.next)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.MusicPlayerCustomView,
            0, 0).apply {
            play_pause.setBackgroundResource(getResourceId(R.styleable.MusicPlayerCustomView_src, R.drawable.play_pause))
        }.recycle()

    }
}