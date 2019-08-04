package jedi.mobi.challenge.trivagostarwars.repository.network.response

import kotlinx.serialization.Serializable

@Serializable
internal data class PeopleListResponse(
    val results: List<PeopleResponse>
)