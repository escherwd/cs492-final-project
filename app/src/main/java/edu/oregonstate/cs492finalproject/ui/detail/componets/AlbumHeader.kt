package edu.oregonstate.cs492finalproject.ui.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import edu.oregonstate.cs492finalproject.data.model.Album

@Composable
fun AlbumHeader(
    album: Album,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = album.coverUrl,
            contentDescription = album.title,
            modifier = Modifier
                .size(140.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = album.title,
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = album.artist,
                style = MaterialTheme.typography.titleMedium
            )

            album.year?.let { year ->
                Text(
                    text = year,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            album.reviewCount?.let { count ->
                Text(
                    text = "Reviews: $count",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}