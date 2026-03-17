package edu.oregonstate.cs492finalproject.data

import android.R
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
    val title: String
): Serializable