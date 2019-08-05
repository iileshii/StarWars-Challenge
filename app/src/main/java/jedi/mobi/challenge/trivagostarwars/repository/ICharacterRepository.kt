package jedi.mobi.challenge.trivagostarwars.repository

import androidx.lifecycle.LiveData
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacter

interface ICharacterRepository {
    fun loadCharacter(id: Long): LiveData<StarWarsCharacter>
}
