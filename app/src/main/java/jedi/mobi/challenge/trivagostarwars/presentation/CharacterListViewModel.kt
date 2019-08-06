package jedi.mobi.challenge.trivagostarwars.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacterListItem
import jedi.mobi.challenge.trivagostarwars.repository.RepositoryFabric

class CharacterListViewModel : ViewModel() {

    private var currentQuery: String? = ""
    private var liveData: LiveData<List<StarWarsCharacterListItem>>? = null
    private val repository by lazy { RepositoryFabric.getCharacterListRepository() }

    fun getCharacterList(query: String?): LiveData<List<StarWarsCharacterListItem>> {
        val optimized = query.orEmpty()
        return liveData?.let {
            if (currentQuery == optimized) {
                it
            } else {
                updateCurrentList(optimized)
            }
        } ?: updateCurrentList(optimized)
    }

    private fun updateCurrentList(query: String) =
        liveDataSource(query)
            .also {
                currentQuery = query
                liveData = it
            }

    private fun liveDataSource(query: String?) =
        if (query == null) {
            MutableLiveData()
        } else {
            loadCharacterList(query)
        }

    private fun loadCharacterList(query: String) = repository.loadList(query)
}