package jedi.mobi.challenge.trivagostarwars.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacterListItem
import jedi.mobi.challenge.trivagostarwars.repository.network.ApiProvider
import jedi.mobi.challenge.trivagostarwars.repository.network.response.toStarWarsCharacterListItem

internal class CharacterListRepository private constructor() : ICharacterListRepository {

    companion object {
        val instance: ICharacterListRepository by lazy { CharacterListRepository() }
    }

    private val api = ApiProvider.api

    override fun loadList(): LiveData<List<StarWarsCharacterListItem>> {
        return liveData {
            val source = api.getPeople().results.map { it.toStarWarsCharacterListItem() }
            emit(source)
        }
    }
}