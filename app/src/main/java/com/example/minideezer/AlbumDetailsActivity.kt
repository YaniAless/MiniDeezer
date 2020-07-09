package com.example.minideezer

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.details_row.view.*
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class AlbumDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)

        val navBarTitle = intent.getStringExtra(CustomViewHolder.ALBUM_TITLE)
        supportActionBar?.title = navBarTitle

        fetchJSON()
    }

    private fun fetchJSON() {
        val albumId = intent.getIntExtra(CustomViewHolder.ALBUM_ID, -1)
        val albumDetailUrl = "https://api.deezer.com/2.0/album/"+albumId+"/tracks"

        val client = OkHttpClient()
        val request = Request.Builder().url(albumDetailUrl).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                //println(body)

                val gson = GsonBuilder().create()
                val albumList = gson.fromJson(body, TrackList::class.java)

                runOnUiThread {
                    if(albumList.data != null) {
                        recyclerView_main.adapter = AlbumDetailsAdapter(albumList)
                    }
                }

            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute the request")
            }
        })
    }

    private class AlbumDetailsAdapter(val trackList: TrackList): RecyclerView.Adapter<AlbumDetailsViewHolder>() {

        override fun getItemCount(): Int {
            return trackList.data.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val customView = layoutInflater.inflate(R.layout.details_row, parent, false)

            return AlbumDetailsViewHolder(customView)
        }

        override fun onBindViewHolder(holder: AlbumDetailsViewHolder, position: Int) {
            val albumDetails = trackList.data[position]

            holder.customView.textView_music_title?.text = albumDetails.title

            holder.customView.textView_duration?.text = convertDuration(albumDetails.duration)

            holder.track = albumDetails
        }

        private fun convertDuration(duration: Int): String
        {
            val minutes = (duration % 3600) / 60
            val seconds = duration % 60

            return String.format("%02d:%02d",minutes, seconds)
        }

    }

    class AlbumDetailsViewHolder(val customView: View, var track: Track? = null): RecyclerView.ViewHolder(customView) {

        companion object {
            val SONG_LINK_KEY = "SONG_LINK"
            val SONG_TITLE_KEY = "SONG_TITLE"
            val ARTIST_NAME_KEY = "ARTIST_NAME"
        }

        init {
            customView.setOnClickListener {

                val intent = Intent(customView.context, TrackActivity::class.java)

                intent.putExtra(SONG_LINK_KEY, track?.preview)
                intent.putExtra(SONG_TITLE_KEY, track?.title)
                intent.putExtra(ARTIST_NAME_KEY, track?.artist?.name)

                customView.context.startActivity(intent)
            }
        }
    }
}
