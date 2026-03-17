package edu.oregonstate.cs492finalproject.data

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RecordClubService {
//    @GET("search/repositories")
//    suspend fun searchRepositories(
//        @Query("q") query: String,
//        @Query("sort") sort: String = "stars"
//    ): Response<GitHubSearchResults>

    enum class RecordClubReleaseSort(val key: String) {
        popularityWeek("popularity-week"),
        popularityMonth("popularity-month")
    }

    @GET("releases")
    suspend fun getReleases(
        @Query("limit") limit: Int,
        @Query("sortBy") sortBy: String
    ): Response<ReleaseListResults>

    @POST("search/v2")
    suspend fun searchReleases(
        @Body searchBody: RecordClubSearchBody
    ): Response<ReleaseListResults>

    companion object {
        private val BASE_URL = "https://record.club/api/"

        // GitHubService.create()
        fun create(): RecordClubService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(RecordClubService::class.java)
        }
    }
}