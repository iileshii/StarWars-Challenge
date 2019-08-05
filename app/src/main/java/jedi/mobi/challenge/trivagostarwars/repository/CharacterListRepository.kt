package jedi.mobi.challenge.trivagostarwars.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacterListItem
import jedi.mobi.challenge.trivagostarwars.repository.network.IApi
import jedi.mobi.challenge.trivagostarwars.repository.network.getIdFromUrl
import jedi.mobi.challenge.trivagostarwars.repository.network.response.PeopleResponse

internal class CharacterListRepository(private val api: IApi) : ICharacterListRepository {

    override fun loadList(): LiveData<List<StarWarsCharacterListItem>> {
        return liveData {
            val source = api.getPeople().results.map(::mapStarWarsCharacterListItem)
            emit(source)
        }
    }

    private fun mapStarWarsCharacterListItem(peopleResponse: PeopleResponse): StarWarsCharacterListItem {
        return StarWarsCharacterListItem(
            peopleResponse.url.getIdFromUrl(),
            peopleResponse.name,
            peopleResponse.birthYear
        )
    }
}