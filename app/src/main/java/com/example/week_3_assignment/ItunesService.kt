package com.example.week_3_assignment

import com.example.week_3_assignment.RecyclerView.ItemsViewModel
import retrofit2.Call
import retrofit2.http.GET

interface ItunesService {

    @GET("https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50")
    fun getAllItunes() : Call<ItemsViewModel>

}