package com.example.minideezer.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minideezer.R
import com.example.minideezer.Models.TrackList
import com.example.minideezer.ViewHolders.AlbumDetailsViewHolder
import kotlinx.android.synthetic.main.details_row.view.*

class AlbumDetailsAdapter(private val trackList: TrackList): RecyclerView.Adapter<AlbumDetailsViewHolder>() {

    override fun getItemCount(): Int {
        return this.trackList.data.size
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