package com.example.minideezer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContext = applicationContext
        setContentView(R.layout.activity_main)

//      recyclerView_main.setBackgroundColor(Color.BLUE)

        recyclerView_main.layoutManager = LinearLayoutManager(this)

        fetchJson()
    }

    private fun fetchJson() {
        println("Attempting to Fetch JSON")

        val url = "https://api.deezer.com/2.0/user/2529/albums"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                runOnUiThread {
                    if(homeFeed.data != null)
                    {
                        recyclerView_main.adapter = MainAdapter(homeFeed)
                    }

                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute the request")
            }
        })
    }

    companion object {

        lateinit  var appContext: Context

    }
}

