package jedi.mobi.challenge.trivagostarwars.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jedi.mobi.challenge.trivagostarwars.domain.Planet
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacter

class CharacterViewModel : ViewModel() {

    private var currentId: Long? = null
    private var liveData: LiveData<StarWarsCharacter>? = null

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

    private fun loadCharacter(id: Long): LiveData<StarWarsCharacter> {
        //todo add loading data from repository
        return MutableLiveData<StarWarsCharacter>().apply {
            value = StarWarsCharacter("TestName", "brth year test", 1f, 1f, 1f, emptyList(), Planet(100L), emptyList())
        }
    }
}