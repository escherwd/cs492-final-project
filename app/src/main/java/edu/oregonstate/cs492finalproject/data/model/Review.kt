package edu.oregonstate.cs492finalproject.data.model

data class Review(
    val id: String = "",
    val author: String,
    val rating: Double? = null,
    val body: String,
    val likes: Int? = null,
    val reviewUrl: String? = null
)