package jedi.mobi.challenge.trivagostarwars.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacter
import jedi.mobi.challenge.trivagostarwars.repository.network.ApiProvider
import jedi.mobi.challenge.trivagostarwars.repository.network.response.toStarWarsCharacter


internal class CharacterRepository private constructor() : ICharacterRepository {

    companion object {
        val instance: ICharacterRepository by lazy { CharacterRepository() }
    }

    private val api = ApiProvider.api

    override fun loadPeople(id: Long): LiveData<StarWarsCharacter> {
        return liveData {
            emit(api.getPeople(id).toStarWarsCharacter())
        }
    }
}