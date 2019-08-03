package jedi.mobi.challenge.trivagostarwars.repository

import androidx.lifecycle.LiveData
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacter

interface ICharacterRepository {
    fun loadItem(id: Long): LiveData<StarWarsCharacter>
}
