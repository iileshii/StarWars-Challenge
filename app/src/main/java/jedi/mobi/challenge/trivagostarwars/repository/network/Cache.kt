package jedi.mobi.challenge.trivagostarwars.repository.network

internal class Cache<T> : ICache<T> {

    private val map = hashMapOf<Long, T>()

    override fun put(id: Long, response: T) {
        response?.let { map[id] = it }
    }

    override fun has(id: Long): Boolean {
        return map.contains(id)
    }

    override fun get(id: Long): T? {
        return map[id]
    }

    override fun invalidate() {
        map.clear()
    }
}