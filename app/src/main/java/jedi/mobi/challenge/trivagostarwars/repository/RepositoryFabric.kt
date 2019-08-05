package jedi.mobi.challenge.trivagostarwars.repository

import jedi.mobi.challenge.trivagostarwars.repository.network.ApiProvider
import jedi.mobi.challenge.trivagostarwars.repository.network.IApi

object RepositoryFabric {

    private val characterRepository by lazy { CharacterRepository(getApi()) }
    private val characterListRepository by lazy { CharacterListRepository(getApi()) }


    fun getCharacterRepository(): ICharacterRepository {
        return characterRepository
    }

    fun getCharacterListRepository(): ICharacterListRepository {
        return characterListRepository
    }

    private fun getApi(): IApi {
        return ApiProvider.api
    }
}