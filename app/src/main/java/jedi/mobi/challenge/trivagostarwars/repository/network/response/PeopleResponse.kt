package jedi.mobi.challenge.trivagostarwars.repository.network.response

import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacter
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacterListItem
import jedi.mobi.challenge.trivagostarwars.repository.network.getIdFromUrl
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PeopleResponse(
    val name: String,
    @SerialName("birth_year") val birthYear: String,
    val height: String,
    val species: List<String>,
    val url: String
)

internal fun PeopleResponse.toStarWarsCharacter(): StarWarsCharacter {
    val heightCm = height.toFloat()
    val heightInches = inchFromCm(heightCm)
    val heightFeet = feetFromInch(heightInches)

    return StarWarsCharacter(
        url.getIdFromUrl(),
        name,
        birthYear,
        heightCm,
        heightInches,
        heightFeet,
        emptyList(), // todo
        -1L, //todo
        emptyList() //todo
    )
}

internal fun PeopleResponse.toStarWarsCharacterListItem(): StarWarsCharacterListItem {
    return StarWarsCharacterListItem(url.getIdFromUrl(), name, birthYear)
}

private fun inchFromCm(cm: Float): Float {
    return cm / 2.54f
}

private fun feetFromInch(inches: Float): Float {
    return inches / 12f
}