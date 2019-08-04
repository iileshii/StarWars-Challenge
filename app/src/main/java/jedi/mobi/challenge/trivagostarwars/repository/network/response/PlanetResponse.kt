package jedi.mobi.challenge.trivagostarwars.repository.network.response

import kotlinx.serialization.Serializable

@Serializable
internal data class PlanetResponse(
    val name: String,
    val population: String,
    val url: String
)