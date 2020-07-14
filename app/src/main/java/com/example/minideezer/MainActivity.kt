package com.example.minideezer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minideezer.TrackActivity.Companion.mediaPlayer
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContext = applicationContext
        setContentView(R.layout.activity_main)

        val playPause: Button = findViewById(R.id.play_pause)
        val musicPlayer: MusicPlayerCustomView = findViewById(R.id.music_player)

        recyclerView_main.layoutManager = LinearLayoutManager(this)

        setPlayer(playPause, musicPlayer)
        fetchJson()
    }

    private fun fetchJson() {
        println("Attempting to Fetch JSON")

        val url = "https://api.deezer.com/2.0/user/2529/albums"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println("ALBUM LIST : $body")

                val gson = GsonBuilder().create()

                val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                runOnUiThread {
                    if(homeFeed.data != null)
                    {
                        recyclerView_main.adapter = MainAdapter(homeFeed)
                    }
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute the request")
            }
        })
    }

    private fun setPlayer(playPauseButton: Button, musicPlayer: MusicPlayerCustomView)
    {
        if(mediaPlayer.isPlaying)
        {
            playPauseButton.setOnClickListener {
                if(mediaPlayer.isPlaying)
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

    companion object {
        lateinit  var appContext: Context
    }
}

