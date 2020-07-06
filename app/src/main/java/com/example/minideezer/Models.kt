package com.example.minideezer


class HomeFeed(val data: List<Data>)

class Data(val id: Int, val title: String, val cover_medium: String, val artist: Artist)

class Artist(val name: String, val picture: String)
