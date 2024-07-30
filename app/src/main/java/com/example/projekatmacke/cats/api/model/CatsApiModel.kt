package com.example.projekatmacke.cats.api.model

import kotlinx.serialization.Serializable

@Serializable
data class CatsApiModel(
    val id:String,
    val name:String,
    val alt_names: String? = null,
    val description: String,
    val temperament: String,
    // ostatak
    val origin: String,
    val life_span:String,
    val wikipedia_url:String? = null,
    val rare:Int,
    val adaptability:Int,
    val energy_level: Int,
    val intelligence: Int,
    val stranger_friendly: Int,
    val dog_friendly: Int,
    val reference_image_id : String? = null,
    val weight:Weight,

)

@Serializable
data class Weight(
    val imperial:String,
    val metric: String,
)

//**{
//    "weight": {
//        "imperial": "7  -  10",
//        "metric": "3 - 5"
//    },
//    "id": "abys",
//    "name": "Abyssinian",
//    "cfa_url": "http://cfa.org/Breeds/BreedsAB/Abyssinian.aspx",
//    "vetstreet_url": "http://www.vetstreet.com/cats/abyssinian",
//    "vcahospitals_url": "https://vcahospitals.com/know-your-pet/cat-breeds/abyssinian",
//    "temperament": "Active, Energetic, Independent, Intelligent, Gentle",
//    "origin": "Egypt",
//    "country_codes": "EG",
//    "country_code": "EG",
//    "description": "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cat and love both people and other animals.",
//    "life_span": "14 - 15",
//    "indoor": 0,
//    "lap": 1,
//    "alt_names": "",
//    "adaptability": 5,
//    "affection_level": 5,
//    "child_friendly": 3,
//    "dog_friendly": 4,
//    "energy_level": 5,
//    "grooming": 1,
//    "health_issues": 2,
//    "intelligence": 5,
//    "shedding_level": 2,
//    "social_needs": 5,
//    "stranger_friendly": 5,
//    "vocalisation": 1,
//    "experimental": 0,
//    "hairless": 0,
//    "natural": 1,
//    "rare": 0,
//    "rex": 0,
//    "suppressed_tail": 0,
//    "short_legs": 0,
//    "wikipedia_url": "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
//    "hypoallergenic": 0,
//    "reference_image_id": "0XYvRd7oD"
//}**//