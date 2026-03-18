package edu.oregonstate.cs492finalproject.ui.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import edu.oregonstate.cs492finalproject.data.model.Track

@Composable
fun TrackRow(
    track: Track,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${track.number}. ${track.title}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )

        track.duration?.let { duration ->
            Text(
                text = duration,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}