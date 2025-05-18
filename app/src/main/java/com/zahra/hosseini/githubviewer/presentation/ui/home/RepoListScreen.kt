package com.zahra.hosseini.githubviewer.presentation.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zahra.hosseini.githubviewer.presentation.viewmodel.GitHubViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoListScreen(
    viewModel: GitHubViewModel = hiltViewModel(),
) {
    val repos by viewModel.repos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    var username by remember { mutableStateOf("freeCodeCamp") }

    val refreshing = viewModel.isLoading.collectAsState().value
    val state = rememberPullToRefreshState()

    LaunchedEffect(Unit) {
        viewModel.fetchUserRepos(username)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        // Username Input
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("GitHub Username") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.fetchUserRepos(username) },
            modifier = Modifier.align(Alignment.End),
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))


        when {
            isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            error != null -> {
                Text(
                    text = error ?: "Unknown error",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
            }

            repos.isEmpty() -> {
                Text(
                    "No repositories found.",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
            }

            else -> {

                PullToRefreshBox(
                    isRefreshing = refreshing,
                    state = state,
                    onRefresh = {
                        viewModel.fetchUserRepos(username)
                    },
                )
                {
                    LazyColumn(
                        contentPadding = PaddingValues(bottom = 80.dp),
                    ) {
                        items(repos) { repo ->
                            RepoItem(repo)
                            Divider()
                        }
                    }
                }
            }
        }
    }
}