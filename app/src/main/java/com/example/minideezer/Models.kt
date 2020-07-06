package com.example.minideezer


class HomeFeed(val data: List<Data>)

class TrackList(val data: List<AlbumDetails>)

class Data(val id: Int, val title: String, val cover_medium: String, val artist: Artist)

class Artist(val name: String, val picture: String)

class AlbumDetails(val id: Int, val title: String, val duration: Int, val track_position: Int, val link: String)
