package com.example.minideezer.CustomViews


import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.example.minideezer.R

class MusicPlayerCustomView(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    private var playPause: Button
    private var previous: Button
    private var next: Button

    init {
        View.inflate(context, R.layout.music_player, this)

        playPause = findViewById(R.id.play_pause)
        previous = findViewById(R.id.previous)
        next = findViewById(R.id.next)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.MusicPlayerCustomView,
            0, 0).apply {
            playPause.setBackgroundResource(getResourceId(
                R.styleable.MusicPlayerCustomView_src,
                R.drawable.play_pause
            ))
        }.recycle()

    }
}