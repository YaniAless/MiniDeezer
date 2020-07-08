package com.example.minideezer

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.track.*

class TrackActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.track)

        val courseLink = intent.getStringExtra(AlbumDetailsActivity.AlbumDetailsViewHolder.SONG_LINK_KEY)

        println(courseLink)

        webview_course_lesson.settings.javaScriptEnabled = true
        webview_course_lesson.settings.loadWithOverviewMode = true
        webview_course_lesson.settings.useWideViewPort = true

        webview_course_lesson.loadUrl("https://www.deezer.com/track/313640")



    }

}