package com.zahra.hosseini.githubviewer.domain.model

data class Repo(
    val id: Long,
    val name: String,
    val description: String?,
    val htmlUrl: String
)
