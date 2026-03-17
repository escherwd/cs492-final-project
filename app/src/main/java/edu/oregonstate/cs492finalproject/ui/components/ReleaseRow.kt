package edu.oregonstate.cs492finalproject.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import edu.oregonstate.cs492finalproject.data.ReleaseListResult

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ReleaseRow(release: ReleaseListResult) {
    Row(Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {

        Box(Modifier.size(70.dp).background(MaterialTheme.colorScheme.surfaceContainer).clip(RoundedCornerShape(4.dp))) {
            GlideImage(
                model = release.imageUrl(70 * 2),
                contentDescription = "Cover image",
                contentScale = ContentScale.FillBounds
            )
        }

        Spacer(Modifier.size(20.dp))

        Column(Modifier.weight(1.0f)) {
            Text(release.title, style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.size(4.dp))
            Text(release.artists.joinToString(", ") { it.artist.name }, style = MaterialTheme.typography.bodyMedium)
        }

        if (release.stats?.rating?.average != null) {

            Box(
                Modifier.clip(RoundedCornerShape(6.dp))
                    .background(MaterialTheme.colorScheme.primary).padding(8.dp)
            ) {
                Text(
                    "%.1f".format(release.stats.rating.average),
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

//        Row(Modifier.padding(start = 8.dp)) {
//            Icon(Icons.Default.Star, "Rating", modifier = Modifier.size(24.dp))
//            Spacer(Modifier.size(4.dp))
//
//        }



    }
}