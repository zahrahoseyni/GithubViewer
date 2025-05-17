package com.zahra.hosseini.githubviewer.di

import com.zahra.hosseini.githubviewer.data.api.GitHubApiService
import com.zahra.hosseini.githubviewer.data.repository.GitHubRepositoryImpl
import com.zahra.hosseini.githubviewer.domain.repository.GitHubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGitHubRepository(
        apiService: GitHubApiService
    ): GitHubRepository = GitHubRepositoryImpl(apiService)
}
