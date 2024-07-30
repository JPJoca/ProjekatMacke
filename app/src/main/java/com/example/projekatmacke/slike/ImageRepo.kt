package com.example.projekatmacke.slike

import com.example.projekatmacke.network.retrofit
import com.example.projekatmacke.slike.api.ImageApi
import com.example.projekatmacke.slike.api.Model.ImageApiModel

object ImageRepo {

    private val imageRepo: ImageApi = retrofit.create(ImageApi::class.java)

    suspend fun fetchImage(imageId: String?): ImageApiModel {
        println(imageId)
        return imageRepo.getImage(imageId = imageId)
    }
}