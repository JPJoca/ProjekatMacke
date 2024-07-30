package com.example.projekatmacke.slike.api

import com.example.projekatmacke.slike.api.Model.ImageApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageApi {

    @GET("images/{image_id}")
    suspend fun getImage(
        @Path("image_id") imageId:String?,
    ): ImageApiModel
}