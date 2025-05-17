package com.zahra.hosseini.githubviewer.data.repository

import com.zahra.hosseini.githubviewer.data.api.GitHubApiService
import com.zahra.hosseini.githubviewer.data.model.toDomainModel
import com.zahra.hosseini.githubviewer.domain.model.Repo
import com.zahra.hosseini.githubviewer.domain.repository.GitHubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubRepositoryImpl @Inject constructor(
    private val apiService: GitHubApiService
) : GitHubRepository {

    override fun getUserRepos(username: String): Flow<List<Repo>> = flow {
        val response = apiService.getUserRepos(username)
        if (response.isSuccessful) {
            val repoDtos = response.body() ?: emptyList()
            val repos = repoDtos.map { it.toDomainModel() }
            emit(repos)
        } else {
            throw HttpException(response)
        }
    }.catch { e ->
        // Emit a Result.Error here instead if using sealed Result type
        throw e
    }.flowOn(Dispatchers.IO)
}
