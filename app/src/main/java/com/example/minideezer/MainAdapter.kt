package com.example.minideezer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_row.view.*

class MainAdapter(private val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder>() {

    private val context = MainActivity.appContext

    // numberOfItems
    override fun getItemCount(): Int {
        return homeFeed.data.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // how do we even create a view
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.album_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val album = homeFeed.data[position]
        holder.view.textView_album_title?.text = album.title

        holder.view.textView_artist_name?.text = album.artist.name

        val thumbnailImageView = holder.view.imageView_album_thumbnail
        Picasso.with(holder.view.context).load(album.cover_medium).into(thumbnailImageView)

        //FADE IN
        val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        thumbnailImageView.startAnimation(animation)

        val channelProfileImageView = holder.view.imageView_artist_profile
        Picasso.with(holder.view.context).load(album.artist.picture).into(channelProfileImageView)
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}