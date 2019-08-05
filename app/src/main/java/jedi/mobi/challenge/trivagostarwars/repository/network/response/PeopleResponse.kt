package jedi.mobi.challenge.trivagostarwars.repository.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PeopleResponse(
    val name: String,
    @SerialName("birth_year") val birthYear: String,
    val height: String,
    val species: List<String>,
    @SerialName(value = "homeworld") val homeWorld: String,
    val films: List<String>,
    val url: String
)