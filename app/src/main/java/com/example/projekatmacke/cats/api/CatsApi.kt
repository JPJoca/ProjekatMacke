package com.example.projekatmacke.cats.api

import com.example.projekatmacke.cats.api.model.CatsApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CatsApi {
    @GET("breeds")
    suspend fun getAllCats(): List<CatsApiModel>

    @GET("breeds/{breed_id}")
    suspend fun getOneCat(
        @Path("breed_id") catId:String,
    ): CatsApiModel
}