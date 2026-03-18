package edu.oregonstate.cs492finalproject.ui.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.floor

@Composable
fun RatingSection(
    rating: Double?,
    modifier: Modifier = Modifier
) {
    if (rating == null) {
        Text(
            text = "No rating available",
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier
        )
        return
    }

    val filledStars = floor(rating).toInt()
    val hasHalfStar = (rating - filledStars) >= 0.5
    val emptyStars = 5 - filledStars - if (hasHalfStar) 1 else 0

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        repeat(filledStars) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Filled star"
            )
        }

        if (hasHalfStar) {
            Icon(
                imageVector = Icons.Default.StarHalf,
                contentDescription = "Half star"
            )
        }

        repeat(emptyStars) {
            Icon(
                imageVector = Icons.Default.StarBorder,
                contentDescription = "Empty star"
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = String.format("%.1f / 5", rating),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}