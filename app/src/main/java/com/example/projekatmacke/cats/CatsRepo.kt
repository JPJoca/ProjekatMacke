package com.example.projekatmacke.cats

import com.example.projekatmacke.cats.api.CatsApi
import com.example.projekatmacke.cats.api.model.CatsApiModel
import com.example.projekatmacke.network.retrofit

object CatsRepo {

    private val catApi: CatsApi = retrofit.create(CatsApi::class.java)

    suspend fun fetchAllCats(): List<CatsApiModel> {

        return catApi.getAllCats()
    }

    suspend fun fetchOneCat(catId: String): CatsApiModel {

        return catApi.getOneCat(catId = catId)
    }
}