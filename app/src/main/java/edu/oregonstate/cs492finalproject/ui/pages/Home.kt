package edu.oregonstate.cs492finalproject.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import edu.oregonstate.cs492finalproject.ui.components.ReleaseRow
import edu.oregonstate.cs492finalproject.ui.navigation.Page
import edu.oregonstate.cs492finalproject.ui.view_models.ReleasesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navigationController: NavController) {

    val viewModel: ReleasesViewModel = viewModel()
    val releases = viewModel.releases.value
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text("Popular Albums")
                },
                actions = {
                    IconButton(onClick = {
//                        Go to search page
                        navigationController.navigate(route = Page.Search.route)
                    }) {
                        Icon(
                            Icons.Default.Search,
                            "Search"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->

        if (releases == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier.padding(paddingValues).verticalScroll(rememberScrollState())
            ) {
                releases.forEach {
                    ReleaseRow(it)
                }
            }
        }
    }
}