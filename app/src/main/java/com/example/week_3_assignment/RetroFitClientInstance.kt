package com.example.week_3_assignment

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClientInstance {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://itunes.apple.com/search?term=rock&amp;media=music&amp;entity=song&amp;limit=50"


    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }
}