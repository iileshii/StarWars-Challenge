package jedi.mobi.challenge.trivagostarwars.presentation

import androidx.lifecycle.ViewModel
import jedi.mobi.challenge.trivagostarwars.repository.RepositoryFabric

class CharacterListViewModel : ViewModel() {

    private val repository by lazy { RepositoryFabric.getCharacterListRepository() }

    fun getCharacterList(query: String = "") = repository.loadList(query)
}