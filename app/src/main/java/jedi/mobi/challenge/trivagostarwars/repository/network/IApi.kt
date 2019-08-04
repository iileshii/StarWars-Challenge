package jedi.mobi.challenge.trivagostarwars.repository.network

import jedi.mobi.challenge.trivagostarwars.repository.network.response.FilmResponse
import jedi.mobi.challenge.trivagostarwars.repository.network.response.PeopleResponse
import jedi.mobi.challenge.trivagostarwars.repository.network.response.PlanetResponse
import jedi.mobi.challenge.trivagostarwars.repository.network.response.SpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface IApi {

    @GET("people/")
    suspend fun getPeople(): List<PeopleResponse>

    @GET("people/{id}/")
    suspend fun getPeople(@Path("id") id: Long): PeopleResponse

    @GET("films/{id}/")
    suspend fun getFilm(@Path("id") id: Long): FilmResponse

    @GET("species/{id}")
    suspend fun getSpecies(@Path("id") id: Long): SpeciesResponse

    @GET("planets/{id}")
    suspend fun getPlanet(@Path("id") id: Long): PlanetResponse
}