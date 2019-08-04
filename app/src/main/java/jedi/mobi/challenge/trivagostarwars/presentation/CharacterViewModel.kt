package jedi.mobi.challenge.trivagostarwars.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacter
import jedi.mobi.challenge.trivagostarwars.repository.RepositoryFabric

class CharacterViewModel : ViewModel() {

    private var currentId: Long? = null
    private var liveData: LiveData<StarWarsCharacter>? = null
    private val repository by lazy { RepositoryFabric.getCharacterRepository() }

    fun getCharacter(id: Long): LiveData<StarWarsCharacter> {
        return liveData?.let {
            if (id == currentId) {
                it
            } else {
                updateCurrentCharacter(id)
            }
        } ?: updateCurrentCharacter(id)
    }

    private fun updateCurrentCharacter(id: Long) =
        loadCharacter(id)
            .also {
                currentId = id
                liveData = it
            }

    private fun loadCharacter(id: Long) = repository.loadItem(id)
}