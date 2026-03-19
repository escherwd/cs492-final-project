package edu.oregonstate.cs492finalproject.data

import android.util.Log
import edu.oregonstate.cs492finalproject.data.RecordClubService.RecordClubReleaseSort
import edu.oregonstate.cs492finalproject.data.model.Album
import edu.oregonstate.cs492finalproject.data.model.AlbumDetail
import edu.oregonstate.cs492finalproject.data.model.Track
import edu.oregonstate.cs492finalproject.data.repository.AlbumRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecordClubRepository(
    private val service: RecordClubService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AlbumRepository {
    suspend fun getReleases(
        limit: Int = 25,
        sort: String = RecordClubReleaseSort.popularityMonth.key
    ): Result<List<ReleaseListResult>> =
        withContext(ioDispatcher) {
            try {
                val res = service.getReleases(limit, sort)
                Log.d("fetcher", res.message())
                if (res.isSuccessful) {
                    Result.success(res.body()?.data ?: listOf())
                } else {
                    Result.failure(Exception(res.errorBody()?.string()))
                }
            } catch (e: Exception) {
                Log.d("fetcher", e.message ?: "idk")
                Result.failure(e)
            }
        }

    suspend fun searchReleases(
        limit: Int,
        query: String,
    ): Result<List<ReleaseListResult>> =
        withContext(ioDispatcher) {
            try {
                val res = service.searchReleases(
                    RecordClubSearchBody(
                        query,
                        limit,
                        RecordClubSearchBody.SearchBodyEntities(listOf("albums", "eps", "singles"))
                    )
                )
                Log.d("fetcher", res.message())
                Log.d("fetcher", res.toString())
                if (res.isSuccessful) {
                    Result.success(res.body()?.data ?: listOf())
                } else {
                    Result.failure(Exception(res.errorBody()?.string()))
                }
            } catch (e: Exception) {
                Log.d("fetcher", e.message ?: "idk")
                Result.failure(e)
            }
        }

    override suspend fun getAlbumDetail(albumId: String): AlbumDetail {
        return AlbumDetail(
            Album(
                id = albumId,
                title = "Currents",
                artist = "Tame Impala",
                coverUrl = "https://cdn-images.dzcdn.net/images/cover/de5b9b704cd4ec36f8bf49beb3e17ba2/500x500-000000-80-0-0.jpg",
                year = "2015",
                averageRating = 4.5,
                detailUrl = "https://record.club/releases/albums/tame-impala-currents",
            ),
            description = "Currents is the third studio album by Australian musical project Tame Impala, released on 17 July 2015 by Modular Recordings. It was released by Interscope Records in the United States and by Fiction Records in the United Kingdom, while Caroline International released it in other regions",
            genres = listOf("Rock", "Psychedelic Rock", "Indie Rock"),
            trackList = listOf(
                Track(
                    1,
                    "Let it Happen",
                    "7:48"
                ),
                Track(
                    2,
                    "Nangs",
                    "1:47"
                ),
                Track(
                    3,
                    "The Moment",
                    "4:15"
                ),
                Track(
                    4,
                    "Yes I'm Changing",
                    "4:30"
                ),
                Track(
                    5,
                    "Eventually",
                    "3:19"
                )
            )
        )
    }
}