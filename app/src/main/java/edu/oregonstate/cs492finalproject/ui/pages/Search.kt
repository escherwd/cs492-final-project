package edu.oregonstate.cs492finalproject.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.layout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import edu.oregonstate.cs492finalproject.ui.components.ReleaseRow
import edu.oregonstate.cs492finalproject.ui.view_models.ReleasesViewModel
import edu.oregonstate.cs492finalproject.ui.view_models.SearchViewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(navigationController: NavController) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val focusRequester = remember { FocusRequester() }

    var query = remember { mutableStateOf("") }

    val viewModel: SearchViewModel = viewModel()

    val results = viewModel.results.value

    Scaffold(
        topBar = {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(TopAppBarDefaults.windowInsets)
                    .focusRequester(focusRequester)
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        val height = placeable.height * (1 - scrollBehavior.state.collapsedFraction)
                        layout(placeable.width, height.roundToInt()) {
                            placeable.place(0, 0)
                        }
                    },
                value = query.value,
                placeholder = { Text("Search for Albums") },
                onValueChange = {
                    query.value = it
                    if (it.length > 2) {
                        viewModel.searchReleases(15, it)
                    }
                },
                leadingIcon = {
                    IconButton({
                        navigationController.navigateUp()
                    }) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, "Back")
                    }
                },
                trailingIcon = if (query.value.isNotBlank()) {
                    {
                        IconButton({
                            query.value = ""
                        }) {
                            Icon(Icons.Default.Clear, "Clear")
                        }
                    }
                } else {
                    null
                }
            )
        }
    ) { paddingValues ->
        if (results?.size == 0) {
            {}
        }
        if (results == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                results.forEach {
                    ReleaseRow(it)
                }
            }
        }

    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}