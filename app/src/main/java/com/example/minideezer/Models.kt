package com.example.minideezer


class HomeFeed(val data: List<Album>)

class TrackList(val data: List<Track>)

class Album(val id: Int, val title: String, val cover_medium: String, val artist: Artist)

class Artist(val name: String, val picture: String)

class Track(val id: Int, val title: String, val duration: Int, val track_position: Int, val link: String, val preview: String, val artist: TrackArtist)

//object Artist in the Tracks API link
class TrackArtist(val name: String)
