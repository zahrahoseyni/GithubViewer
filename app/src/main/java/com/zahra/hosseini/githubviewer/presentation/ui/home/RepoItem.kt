package com.zahra.hosseini.githubviewer.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zahra.hosseini.githubviewer.domain.model.Repo

@Composable
fun RepoItem(repo: Repo) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {

        Text(text = repo.name, style = MaterialTheme.typography.h5)
        if (!repo.description.isNullOrBlank()) {
            Text(text = repo.description, style = MaterialTheme.typography.body2)
        }
        Text(
            text = repo.htmlUrl,
            style = MaterialTheme.typography.subtitle1.copy(color = Color.Blue)
        )
    }
}