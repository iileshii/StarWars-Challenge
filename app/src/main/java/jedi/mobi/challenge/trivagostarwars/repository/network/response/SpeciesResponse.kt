package jedi.mobi.challenge.trivagostarwars.repository.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SpeciesResponse(
    val name: String,
    val language: String,
    @SerialName("homeworld") val homeWorld: String?,
    val url: String
)