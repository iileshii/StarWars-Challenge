package jedi.mobi.challenge.trivagostarwars.domain

import java.util.*

data class StarWarsCharacter(
    val name: String,
    val birthYear: String,
    val heightCm: Float,
    val heightInch: Float,
    val heightFeet: Float,
    val species: List<Species>,
    val planet: Planet,
    val films: List<Film>
)

data class Species(
    val name: String,
    val language: String,
    val homeWorld: String
)

data class Planet(val population: Long)

data class Film(
    val title: String,
    val releaseDate: Date,
    val openingCrawl: String
)