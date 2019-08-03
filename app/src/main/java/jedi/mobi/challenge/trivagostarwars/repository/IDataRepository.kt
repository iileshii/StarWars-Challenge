package jedi.mobi.challenge.trivagostarwars.repository

import androidx.lifecycle.LiveData

interface IDataRepository<T> {
    fun loadItem(id: Long): LiveData<T>
}
