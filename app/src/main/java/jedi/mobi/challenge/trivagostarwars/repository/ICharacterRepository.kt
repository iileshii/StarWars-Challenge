package jedi.mobi.challenge.trivagostarwars.repository

import androidx.lifecycle.LiveData
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacter

interface ICharacterRepository {
    fun loadPeople(id: Long): LiveData<StarWarsCharacter>
}
