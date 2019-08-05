package jedi.mobi.challenge.trivagostarwars.repository

import androidx.lifecycle.LiveData
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacterListItem

interface ICharacterListRepository {
    fun loadList(query: String): LiveData<List<StarWarsCharacterListItem>>
}
