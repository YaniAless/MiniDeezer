package com.example.minideezer.ViewHolders

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.minideezer.Models.Album
import com.example.minideezer.Activities.AlbumDetailsActivity


class MusicPlayerCustomViewHolder(val view: View, var album: Album? = null): RecyclerView.ViewHolder(view) {

    companion object {
        const val ALBUM_TITLE = "ALBUM_TITLE"
        const val ALBUM_ID = "ALBUM_ID"
        const val ALBUM_COVER = "ALBUM_COVER"
    }

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, AlbumDetailsActivity::class.java)

            intent.putExtra(ALBUM_TITLE, album?.title)
            intent.putExtra(ALBUM_ID, album?.id)
            intent.putExtra(ALBUM_COVER, album?.cover_medium)

            view.context.startActivity(intent)
        }
    }

}