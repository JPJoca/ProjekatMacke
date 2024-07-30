package com.example.projekatmacke.cats.pojedinacno.model

import com.example.projekatmacke.cats.api.model.Weight

data class OneCatUiModel (
    val id:String,
    val name:String,
    val alt_names: String?,
    val description: String,
    val temperament: String,
    val origin: String,
    val life_span:String,
    val wikipedia_url:String?,
    val adaptability:Int,
    val energy_level: Int,
    val intelligence: Int,
    val stranger_friendly: Int,
    val dog_friendly: Int,
    val weight: Weight,
    val rare:Int,
    val reference_image_id:String? = null,
)