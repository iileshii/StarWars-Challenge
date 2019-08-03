package jedi.mobi.challenge.trivagostarwars.repository

import jedi.mobi.challenge.trivagostarwars.domain.StarWarsCharacter

object RepositoryFabric {

    fun getCharacterRepository(): IDataRepository<StarWarsCharacter> {
        return CharacterRepository.instance
    }
}