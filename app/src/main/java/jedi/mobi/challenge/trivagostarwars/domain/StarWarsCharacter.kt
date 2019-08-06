package jedi.mobi.challenge.trivagostarwars.domain

data class StarWarsCharacter(
    val id: Long,
    val name: String,
    val birthYear: String,
    val heightCm: Float,
    val heightInch: Float,
    val heightFeet: Float,
    val species: List<Species>,
    val population: String,
    val films: List<Film>
)

data class Species(
    val name: String,
    val language: String,
    val homeWorld: String
)

data class Film(
    val title: String,
    val releaseDate: String,
    val openingCrawl: String
)