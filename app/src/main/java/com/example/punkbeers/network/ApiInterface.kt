package com.example.punkbeers.network

import com.example.punkbeers.model.Beer
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("beers")
    suspend fun getBeers(): Response<ArrayList<Beer>>
}