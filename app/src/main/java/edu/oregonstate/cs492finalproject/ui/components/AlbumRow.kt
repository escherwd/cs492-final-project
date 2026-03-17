package edu.oregonstate.cs492finalproject.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AlbumRow() {
    Row(Modifier.fillMaxWidth()) {

        Box(Modifier.size(64.dp).background(MaterialTheme.colorScheme.surfaceContainer)) {

        }

        Spacer(Modifier.size(20.dp))

        Text("Bruh Text", Modifier.background(MaterialTheme.colorScheme.surfaceContainer))

    }
}