package edu.oregonstate.cs492finalproject.ui.view_models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.oregonstate.cs492finalproject.data.RecordClubRepository
import edu.oregonstate.cs492finalproject.data.RecordClubService
import edu.oregonstate.cs492finalproject.data.ReleaseListResult
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {

    val repository = RecordClubRepository(RecordClubService.Companion.create())

    val results = mutableStateOf<List<ReleaseListResult>?>(emptyList())

//    init {
//        fetchReleases(20, RecordClubService.RecordClubReleaseSort.popularityMonth.key)
//    }

    fun searchReleases(limit: Int, query: String) {
        viewModelScope.launch {

            results.value = null

            try {
                val searchRequestResults = repository.searchReleases(limit, query)

                results.value = searchRequestResults.getOrNull()
            } catch (err: Exception) {
                Log.d("fetcher",err.message ?: "idk")
            }

        }
    }

}