package jedi.mobi.challenge.trivagostarwars.domain

import java.util.*

data class StarWarsCharacter(
    val id: Long,
    val name: String,
    val birthYear: String,
    val heightCm: Float,
    val heightInch: Float,
    val heightFeet: Float,
    val species: List<Species>,
    val population: Long,
    val films: List<Film>
)

data class Species(
    val name: String,
    val language: String,
    val homeWorld: String
)

data class Film(
    val title: String,
    val releaseDate: Date,
    val openingCrawl: String
)