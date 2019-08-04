package jedi.mobi.challenge.trivagostarwars.repository.network.response

import kotlinx.serialization.SerialName

internal data class FilmResponse(
    val title: String,
    @SerialName("opening_crawl") val openingCrawl: String,
    @SerialName("release_date") val releaseDate: String,
    val url: String
)