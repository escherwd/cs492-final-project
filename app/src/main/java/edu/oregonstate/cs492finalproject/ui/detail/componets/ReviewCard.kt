package edu.oregonstate.cs492finalproject.ui.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.oregonstate.cs492finalproject.data.model.Review

@Composable
fun ReviewCard(
    review: Review,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = review.author,
                style = MaterialTheme.typography.titleMedium
            )

            review.rating?.let { rating ->
                Text(
                    text = "Rating: ${String.format("%.1f / 5", rating)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Text(
                text = review.body,
                style = MaterialTheme.typography.bodyLarge
            )

            review.likes?.let { likes ->
                Text(
                    text = "$likes likes",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}