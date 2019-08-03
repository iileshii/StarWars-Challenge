package jedi.mobi.challenge.trivagostarwars.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacterListItem
import jedi.mobi.challenge.trivagostarwars.repository.RepositoryFabric

class CharacterListViewModel : ViewModel() {

    private val repository by lazy { RepositoryFabric.getCharacterListRepository() }
    private val liveData: LiveData<List<StarWarsCharacterListItem>> by lazy { repository.loadList() }

    fun getCharacterList(): LiveData<List<StarWarsCharacterListItem>> = liveData

    private fun loadCharacterList() = repository.loadList()
}