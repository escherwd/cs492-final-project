package edu.oregonstate.cs492finalproject.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.oregonstate.cs492finalproject.data.repository.AlbumRepository
import edu.oregonstate.cs492finalproject.ui.detail.components.AlbumHeader
import edu.oregonstate.cs492finalproject.ui.detail.components.RatingSection
import edu.oregonstate.cs492finalproject.ui.detail.components.ReviewCard
import edu.oregonstate.cs492finalproject.ui.detail.components.TrackRow

//Detail Screen is called via repository: AlbumRepository so it needs to be passed like that in Documentation apparently

@Composable
fun DetailScreen(
    albumId: String,
    repository: AlbumRepository,
    modifier: Modifier = Modifier
) {
    val viewModel: DetailViewModel = viewModel(
        factory = DetailViewModel.provideFactory(repository)
    )

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(albumId) {
        viewModel.loadAlbumDetail(albumId)
    }

    when {
        uiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        uiState.error != null -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uiState.error ?: "Unknown error",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        uiState.albumDetail != null -> {
            val detail = uiState.albumDetail!!

            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    AlbumHeader(album = detail.album)
                }

                item {
                    RatingSection(rating = detail.rating)
                }

                item {
                    Text(
                        text = "Track List",
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                if (detail.trackList.isEmpty()) {
                    item {
                        Text(
                            text = "No track list available.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                } else {
                    items(detail.trackList) { track ->
                        TrackRow(track = track)
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Popular Reviews",
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                if (detail.popularReviews.isEmpty()) {
                    item {
                        Text(
                            text = "No reviews available.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                } else {
                    items(detail.popularReviews) { review ->
                        ReviewCard(review = review)
                    }
                }
            }
        }

        else -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No album details available.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}