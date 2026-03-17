package edu.oregonstate.cs492finalproject.data

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class RecordClubSearchBody(
    val query: String,
    val size: Int,
    val entities: SearchBodyEntities
): Serializable {

    @JsonClass(generateAdapter = true)
    data class SearchBodyEntities(
        val releases: List<String>
    ): Serializable

}