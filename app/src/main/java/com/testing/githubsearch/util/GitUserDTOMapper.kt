package com.testing.githubsearch.util

import com.testing.githubsearch.data.domain.GitUser
import com.testing.githubsearch.data.remotemodels.GitUserDTO

object GitUserDTOMapper {

    /*
    * Used to map to the domain model.
    * */
    fun mapToGitUser(gitUserDTO: GitUserDTO): GitUser {
        return GitUser(
            reposUrl = gitUserDTO.reposUrl,
            gistsUrl = gitUserDTO.gistsUrl,
            followingUrl = gitUserDTO.followingUrl,
            starredUrl = gitUserDTO.starredUrl,
            login = gitUserDTO.login,
            followersUrl = gitUserDTO.followersUrl,
            type = gitUserDTO.type,
            url = gitUserDTO.url,
            subscriptionsUrl = gitUserDTO.subscriptionsUrl,
            score = gitUserDTO.score,
            receivedEventsUrl = gitUserDTO.receivedEventsUrl,
            avatarUrl = gitUserDTO.avatarUrl,
            eventsUrl = gitUserDTO.eventsUrl,
            htmlUrl = gitUserDTO.htmlUrl,
            siteAdmin = gitUserDTO.siteAdmin,
            id = gitUserDTO.id,
            gravatarId = gitUserDTO.gravatarId,
            nodeId = gitUserDTO.nodeId,
            organizationsUrl = gitUserDTO.organizationsUrl
        )
    }
}