package edu.oregonstate.cs492finalproject.data.model
// lightweight model for home/search lists
data class Album(
    val id: String,
    val title: String,
    val artist: String,
    val coverUrl: String? = null,
    val year: String? = null,
    val averageRating: Double? = null,
    val reviewCount: Int? = null,
    val detailUrl: String? = null
)