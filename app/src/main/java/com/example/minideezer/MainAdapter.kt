package com.example.minideezer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.album_row.view.*

class MainAdapter: RecyclerView.Adapter<CustomViewHolder>() {

    private val albumTitles = listOf("First title", "Second Title", "3eme", "'4eme")

    // numberOfItems
    override fun getItemCount(): Int {
        return albumTitles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.album_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val videoTitle = albumTitles[position]
        holder.view.textView_album_title?.text = videoTitle
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}