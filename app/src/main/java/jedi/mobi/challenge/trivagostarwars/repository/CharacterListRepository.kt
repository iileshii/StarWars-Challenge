package jedi.mobi.challenge.trivagostarwars.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacterListItem

internal class CharacterListRepository private constructor() : ICharacterListRepository {

    companion object {
        val instance: ICharacterListRepository by lazy { CharacterListRepository() }
    }

    override fun loadList(): LiveData<List<StarWarsCharacterListItem>> {
        //todo remove stub
        return MutableLiveData<List<StarWarsCharacterListItem>>().apply {
            value = listOf(
                StarWarsCharacterListItem(1, "TestName", "brth year test"),
                StarWarsCharacterListItem(2, "SecondCharacter", "1100BBR"),
                StarWarsCharacterListItem(3, "Third", "1100BBR"),
                StarWarsCharacterListItem(11, "TestName", "brth year test"),
                StarWarsCharacterListItem(12, "SecondCharacter", "1100BBR"),
                StarWarsCharacterListItem(13, "Third", "1100BBR"),
                StarWarsCharacterListItem(21, "TestName", "brth year test"),
                StarWarsCharacterListItem(22, "SecondCharacter", "1100BBR"),
                StarWarsCharacterListItem(23, "Third", "1100BBR"),
                StarWarsCharacterListItem(31, "TestName", "brth year test"),
                StarWarsCharacterListItem(32, "SecondCharacter", "1100BBR"),
                StarWarsCharacterListItem(33, "Third", "1100BBR")
            )
        }
    }
}