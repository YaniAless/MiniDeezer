package com.example.minideezer.Activities

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.minideezer.R
import com.example.minideezer.ViewHolders.AlbumDetailsViewHolder

class TrackActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO : Clean this up

        setContentView(R.layout.track)

        val trackTitleTextView: TextView = findViewById(R.id.track_name)
        val artistNameTextView: TextView = findViewById(R.id.artist_name)
        val playPause: Button = findViewById(R.id.play_pause)
        var next :Button = findViewById(R.id.next)
        var previous: Button = findViewById(R.id.previous)

        val trackLink = intent.getStringExtra(AlbumDetailsViewHolder.SONG_LINK_KEY)
        val trackTitle = intent.getStringExtra(AlbumDetailsViewHolder.SONG_TITLE_KEY)
        val artistName = intent.getStringExtra(AlbumDetailsViewHolder.ARTIST_NAME_KEY)

        trackTitleTextView.text = trackTitle
        artistNameTextView.text = artistName

        checkMediaPlayer()

            val mediaPlayer2 = MediaPlayer()
            mediaPlayer = mediaPlayer2
            mediaPlayer.setDataSource(trackLink)
            mediaPlayer.prepare()
            mediaPlayer.start()


        playPause.setOnClickListener {
            if(mediaPlayer.isPlaying)
            {
                mediaPlayer.pause()
            }
            else
            {
                mediaPlayer.start()
            }
        }

    }

    private fun checkMediaPlayer()
    {
        if(mediaPlayer.isPlaying)
        {
            mediaPlayer.release()
        }
    }

    companion object {
        var mediaPlayer = MediaPlayer()
    }

}

