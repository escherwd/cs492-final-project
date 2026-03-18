package edu.oregonstate.cs492finalproject.data.model
//wraps the full detail page content rating tracks and reviews
data class AlbumDetail(
    val album: Album,
    val description: String? = null,
    val genres: List<String> = emptyList(),
    val trackList: List<Track> = emptyList(),
    val rating: Double? = null,
    val popularReviews: List<Review> = emptyList()
)