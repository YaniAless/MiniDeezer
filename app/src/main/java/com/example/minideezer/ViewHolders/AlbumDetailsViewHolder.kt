package com.example.minideezer.ViewHolders

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.minideezer.Models.Track
import com.example.minideezer.Activities.TrackActivity


class AlbumDetailsViewHolder(val customView: View, var track: Track? = null): RecyclerView.ViewHolder(customView) {

    companion object {
        const val SONG_LINK_KEY = "SONG_LINK"
        const val SONG_TITLE_KEY = "SONG_TITLE"
        const val ARTIST_NAME_KEY = "ARTIST_NAME"
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
