package com.zahra.hosseini.githubviewer.data.model

import com.google.gson.annotations.SerializedName
import com.zahra.hosseini.githubviewer.domain.model.Repo

data class RepoDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("html_url") val htmlUrl: String
)

fun RepoDto.toDomainModel(): Repo {
    return Repo(
        id = id,
        name = name,
        description = description,
        htmlUrl = htmlUrl
    )
}