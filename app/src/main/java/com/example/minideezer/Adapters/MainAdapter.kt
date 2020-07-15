package com.example.minideezer.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.minideezer.ViewHolders.MusicPlayerCustomViewHolder
import com.example.minideezer.Models.HomeFeed
import com.example.minideezer.Activities.MainActivity
import com.example.minideezer.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_row.view.*

class MainAdapter(private val homeFeed: HomeFeed): RecyclerView.Adapter<MusicPlayerCustomViewHolder>() {

    private val context =
        MainActivity.appContext

    // numberOfItems
    override fun getItemCount(): Int {
        return homeFeed.data.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicPlayerCustomViewHolder {
        // how do we even create a view
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.album_row, parent, false)
        return MusicPlayerCustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: MusicPlayerCustomViewHolder, position: Int) {
        val album = homeFeed.data[position]
        holder.view.textView_album_title?.text = album.title

        holder.view.textView_artist_name?.text = album.artist.name

        val thumbnailImageView = holder.view.imageView_album_thumbnail
        Picasso.with(holder.view.context).load(album.cover_medium).into(thumbnailImageView)

        val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        thumbnailImageView.startAnimation(animation)

        val artistImageView = holder.view.imageView_artist_profile
        Picasso.with(holder.view.context).load(album.artist.picture).into(artistImageView)

        holder.album = album
    }

}
