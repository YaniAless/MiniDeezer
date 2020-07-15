package com.example.minideezer.Activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minideezer.Adapters.AlbumDetailsAdapter
import com.example.minideezer.CustomViews.MusicPlayerCustomView
import com.example.minideezer.R
import com.example.minideezer.Activities.TrackActivity.Companion.mediaPlayer
import com.example.minideezer.Models.TrackList
import com.example.minideezer.ViewHolders.MusicPlayerCustomViewHolder
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class AlbumDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val playPause: Button = findViewById(R.id.play_pause)
        val mainTitle: TextView = findViewById(R.id.album_list)


        val musicPlayer: MusicPlayerCustomView = findViewById(
            R.id.music_player
        )

        recyclerView_main.layoutManager = LinearLayoutManager(this)

        val albumTitle = intent.getStringExtra(MusicPlayerCustomViewHolder.ALBUM_TITLE)
        mainTitle.text = albumTitle
        supportActionBar?.title = "MiniDeezer by YAL"

            setPlayer(playPause, musicPlayer)

        fetchJSON()
    }

    private fun fetchJSON() {
        val albumId = intent.getIntExtra(MusicPlayerCustomViewHolder.ALBUM_ID, -1)
        val albumDetailUrl = "https://api.deezer.com/2.0/album/$albumId/tracks"

        val client = OkHttpClient()
        val request = Request.Builder().url(albumDetailUrl).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                //println(body)

                val gson = GsonBuilder().create()
                val albumList = gson.fromJson(body, TrackList::class.java)


                runOnUiThread {
                    if(albumList.data != null)
                    {
                        recyclerView_main.adapter = AlbumDetailsAdapter(albumList)
                    }
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute the request")
            }
        })
    }

    private fun setPlayer(playPauseButton: Button, musicPlayer: MusicPlayerCustomView) {

        if (mediaPlayer.isPlaying || mediaPlayer.currentPosition > 1)
        {
            playPauseButton.setOnClickListener {
                if (mediaPlayer.isPlaying)
                {
                    mediaPlayer.pause()
                }
                else
                {
                    mediaPlayer.start()
                }
            }
            musicPlayer.visibility = View.VISIBLE
        }
        else if(!mediaPlayer.isPlaying && mediaPlayer.currentPosition > 1)
        {
            musicPlayer.visibility = View.VISIBLE
        }
    }
}

