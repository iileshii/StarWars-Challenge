package jedi.mobi.challenge.trivagostarwars.repository.network

interface ICache<T> {
    fun put(id: Long, response: T)
    fun has(id: Long): Boolean
    fun get(id: Long): T?
    fun invalidate()
}
