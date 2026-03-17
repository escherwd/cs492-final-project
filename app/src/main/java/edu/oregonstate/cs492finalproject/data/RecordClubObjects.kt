package edu.oregonstate.cs492finalproject.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class ReleaseListResults(
    val success: Boolean,
    val data: List<ReleaseListResult>
): Serializable

@JsonClass(generateAdapter = true)
data class ReleaseListResult(
    val id: String,
    val uri: String,
    val title: String,
    @Json(name = "mbUrl") val musicbrainzUrl: String?,
    val slug: String,
    val updatedAt: String?,
    val releaseDate: ReleaseReleaseDate?,
    val stats: ReleaseStats?,
    val artists: List<ReleaseArtistEntry>
): Serializable {

    // This name is kind of confusing, but it is what it is
    @JsonClass(generateAdapter = true)
    data class ReleaseReleaseDate(
        val month: Int?,
        val day: Int?,
        val year: Int,
    )


    @JsonClass(generateAdapter = true)
    data class ReleaseStats(
        val users: Int,
        val listens: Int,
        val lists: Int,
        val reviews: Int,
        val comments: Int,
        val rotations: Int,
        val queue: Int,
        val favorites: Int,
        val versions: Int,
        val rating: ReleaseRating
    )

    @JsonClass(generateAdapter = true)
    data class ReleaseRating(
        val average: Float,
        val count: Int
    )

    @JsonClass(generateAdapter = true)
    data class ReleaseArtistEntry(
        val name: String,
        val artist: ReleaseArtist
    )

    fun imageUrl(width: Int): String {
        return "https://cdn.rcrd.club/releases/${this.id}.jpg?width=$width"
    }

}

@JsonClass(generateAdapter = true)
data class ReleaseArtist(
    val id: String,
    val uri: String,
    @Json(name = "mbUrl") val musicbrainzUrl: String?,
    val name: String,
    val slug: String,
    @Json(name = "area") val countryOrRegion: String?,
)


