package jedi.mobi.challenge.trivagostarwars.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacter
import jedi.mobi.challenge.trivagostarwars.repository.RepositoryFabric

class CharacterViewModel : ViewModel() {

    private var currentId: Long? = null
    private var liveData: LiveData<StarWarsCharacter>? = null
    private val repository by lazy { RepositoryFabric.getCharacterRepository() }

    fun getCharacter(id: Long?): LiveData<StarWarsCharacter> {
        return liveData?.let {
            if (id != null && id == currentId) {
                it
            } else {
                updateCurrentCharacter(id)
            }
        } ?: updateCurrentCharacter(id)
    }

    private fun updateCurrentCharacter(id: Long?) =
        liveDataSource(id)
            .also {
                currentId = id
                liveData = it
            }

    private fun liveDataSource(id: Long?) =
        if (id == null) {
            MutableLiveData()
        } else {
            loadCharacter(id)
        }

    private fun loadCharacter(id: Long) = repository.loadCharacter(id)
}