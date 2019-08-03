package jedi.mobi.challenge.trivagostarwars.repository

object RepositoryFabric {

    fun getCharacterRepository(): ICharacterRepository {
        return CharacterRepository.instance
    }

    fun getCharacterListRepository(): ICharacterListRepository {
        return CharacterListRepository.instance
    }
}