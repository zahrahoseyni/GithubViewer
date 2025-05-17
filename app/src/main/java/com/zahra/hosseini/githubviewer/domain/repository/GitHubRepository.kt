package com.zahra.hosseini.githubviewer.domain.repository

import com.zahra.hosseini.githubviewer.domain.model.Repo
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {
    fun getUserRepos(username: String): Flow<List<Repo>>
}