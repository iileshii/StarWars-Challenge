package jedi.mobi.challenge.trivagostarwars.repository.network

import jedi.mobi.challenge.trivagostarwars.repository.network.response.*

internal object CachedApiProvider : IApi {

    private val peopleCache = Cache<PeopleResponse>()
    private val planetsCache = Cache<PlanetResponse>()
    private val filmsCache = Cache<FilmResponse>()
    private val speciesCache = Cache<SpeciesResponse>()
    private val api = ApiProvider.api

    override suspend fun getPeople(query: String): PeopleListResponse {
        return api.getPeople(query)
            .also {
                it.results
                    .forEach { peopleResponse ->
                        val id = peopleResponse.url.getIdFromUrl()
                        peopleCache.put(id, peopleResponse)
                    }
            }
    }

    override suspend fun getPeople(id: Long): PeopleResponse {
        return peopleCache.get(id) ?: api.getPeople(id).also { peopleCache.put(id, it) }
    }

    override suspend fun getFilm(id: Long): FilmResponse {
        return filmsCache.get(id) ?: api.getFilm(id).also { filmsCache.put(id, it) }
    }

    override suspend fun getSpecies(id: Long): SpeciesResponse {
        return speciesCache.get(id) ?: api.getSpecies(id).also { speciesCache.put(id, it) }
    }

    override suspend fun getPlanet(id: Long): PlanetResponse {
        return planetsCache.get(id) ?: api.getPlanet(id).also { planetsCache.put(id, it) }
    }
}