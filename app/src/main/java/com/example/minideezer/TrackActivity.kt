package com.example.minideezer

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TrackActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO : Clean this up

        setContentView(R.layout.track)

        val trackTitleTextView: TextView = findViewById(R.id.track_name)
        val artistNameTextView: TextView = findViewById(R.id.artist_name)
        var playPause: Button = findViewById(R.id.play_pause)
        var next :Button = findViewById(R.id.next)
        var previous: Button = findViewById(R.id.previous)

        val trackLink = intent.getStringExtra(AlbumDetailsActivity.AlbumDetailsViewHolder.SONG_LINK_KEY)
        val trackTitle = intent.getStringExtra(AlbumDetailsActivity.AlbumDetailsViewHolder.SONG_TITLE_KEY)
        val artistName = intent.getStringExtra(AlbumDetailsActivity.AlbumDetailsViewHolder.ARTIST_NAME_KEY)

        trackTitleTextView.setText(trackTitle)
        artistNameTextView.setText(artistName)

        val toto = MediaPlayer()

        toto.setDataSource(trackLink)
        toto.prepare()
        toto.start()

        playPause.setOnClickListener {
            if(toto.isPlaying)
            {
                toto.pause()
            }
            else
            {
                toto.start()
            }
        }

    }

}

