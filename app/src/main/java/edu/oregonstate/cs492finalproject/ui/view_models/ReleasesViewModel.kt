package edu.oregonstate.cs492finalproject.ui.view_models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.oregonstate.cs492finalproject.data.RecordClubRepository
import edu.oregonstate.cs492finalproject.data.RecordClubService
import edu.oregonstate.cs492finalproject.data.ReleaseListResult
import kotlinx.coroutines.launch

class ReleasesViewModel: ViewModel() {

    val repository = RecordClubRepository(RecordClubService.Companion.create())

    val releases = mutableStateOf<List<ReleaseListResult>?>(null)

    init {
        fetchReleases(20, RecordClubService.RecordClubReleaseSort.popularityMonth.key)
    }

    fun fetchReleases(limit: Int, sort: String) {
        viewModelScope.launch {

            try {
                val results = repository.getReleases(limit, sort)

                Log.d("fetcher","got results: ${results.isSuccess}")
                releases.value = results.getOrNull()
            } catch (err: Exception) {
                Log.d("fetcher",err.message ?: "idk")
            }

        }
    }

}