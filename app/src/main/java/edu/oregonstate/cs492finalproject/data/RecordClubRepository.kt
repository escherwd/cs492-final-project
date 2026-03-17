package edu.oregonstate.cs492finalproject.data

import android.util.Log
import edu.oregonstate.cs492finalproject.data.RecordClubService.RecordClubReleaseSort
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecordClubRepository(
    private val service: RecordClubService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getReleases(
        limit: Int = 25,
        sort: String = RecordClubReleaseSort.popularityMonth.key
    ): Result<List<ReleaseListResult>> =
        withContext(ioDispatcher) {
            try {
                val res = service.getReleases(limit, sort)
                Log.d("fetcher",res.message())
                if (res.isSuccessful) {
                    Result.success(res.body()?.data ?: listOf())
                } else {
                    Result.failure(Exception(res.errorBody()?.string()))
                }
            } catch (e: Exception) {
                Log.d("fetcher",e.message ?: "idk")
                Result.failure(e)
            }
        }
}