package jedi.mobi.challenge.trivagostarwars.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import jedi.mobi.challenge.trivagostarwars.domain.Film
import jedi.mobi.challenge.trivagostarwars.domain.Species
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacter
import jedi.mobi.challenge.trivagostarwars.repository.network.IApi
import jedi.mobi.challenge.trivagostarwars.repository.network.getIdFromUrl
import jedi.mobi.challenge.trivagostarwars.repository.network.response.FilmResponse
import jedi.mobi.challenge.trivagostarwars.repository.network.response.PeopleResponse
import jedi.mobi.challenge.trivagostarwars.repository.network.response.PlanetResponse
import jedi.mobi.challenge.trivagostarwars.repository.network.response.SpeciesResponse


internal class CharacterRepository(private val api: IApi) : ICharacterRepository {

    override fun loadCharacter(id: Long): LiveData<StarWarsCharacter> {
        return liveData {
            emit(combineAndMapPeople(id))
        }
    }

    private suspend fun combineAndMapPeople(id: Long): StarWarsCharacter {
        val peopleResponse = loadPeople(id)

        val speciesIds = peopleResponse.species.map { it.getIdFromUrl() }
        val homeWorldId = peopleResponse.homeWorld.getIdFromUrl()
        val filmIds = peopleResponse.films.map { it.getIdFromUrl() }

        val speciesResponseList = loadSpecies(speciesIds)
        val planetResponse = loadPlanet(homeWorldId)
        val filmResponseList = loadFilms(filmIds)

        return mapStarWarsCharacter(peopleResponse, speciesResponseList, planetResponse, filmResponseList)
    }

    private suspend fun loadPeople(id: Long) = api.getPeople(id)

    private suspend fun loadSpecies(speciesIds: List<Long>): List<SpeciesResponse> {
        return loadList(speciesIds) { id -> api.getSpecies(id) }
    }

    private suspend fun loadFilms(filmIds: List<Long>): List<FilmResponse> {
        return loadList(filmIds) { id -> api.getFilm(id) }
    }

    private inline fun <T> loadList(ids: List<Long>, loadItem: (id: Long) -> T): List<T> {
        return ids.map { loadItem.invoke(it) }
    }

    private suspend fun loadPlanet(homeWorldId: Long) = api.getPlanet(homeWorldId)

    private suspend fun mapStarWarsCharacter(
        peopleResponse: PeopleResponse,
        speciesResponseList: List<SpeciesResponse>,
        planetResponse: PlanetResponse,
        filmResponseList: List<FilmResponse>
    ): StarWarsCharacter {

        val heightCm = peopleResponse.height.toFloat()
        val heightInches = inchFromCm(heightCm)
        val heightFeet = feetFromInch(heightInches)

        val species: List<Species> = mapSpecies(speciesResponseList)
        val population: String = mapPopulation(planetResponse)
        val films: List<Film> = mapFilms(filmResponseList)

        return StarWarsCharacter(
            peopleResponse.url.getIdFromUrl(),
            peopleResponse.name,
            peopleResponse.birthYear,
            heightCm,
            heightInches,
            heightFeet,
            species,
            population,
            films
        )
    }

    private suspend fun mapSpecies(speciesResponseList: List<SpeciesResponse>): List<Species> {
        return speciesResponseList.map {
            val planetResponse = it.homeWorld?.getIdFromUrl()?.let { id -> loadPlanet(id) }
            Species(it.name, it.language, mapName(planetResponse))
        }
    }

    private fun mapFilms(filmResponseList: List<FilmResponse>): List<Film> {
        return filmResponseList.map { Film(it.title, it.releaseDate, it.openingCrawl) }
    }

    private fun mapPopulation(planetResponse: PlanetResponse): String {
        return planetResponse.population
    }

    private fun mapName(planetResponse: PlanetResponse?): String {
        return planetResponse?.name ?: "n/a"
    }

    private fun inchFromCm(cm: Float): Float {
        return cm / 2.54f
    }

    private fun feetFromInch(inches: Float): Float {
        return inches / 12f
    }
}