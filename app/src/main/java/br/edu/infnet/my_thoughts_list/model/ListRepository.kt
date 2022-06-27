package br.edu.infnet.my_thoughts_list.model

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class ListRepository (private val listDao: ListDao){

    val allThoughts: Flow<List<ListEntity>> = listDao.getAllThoughts()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(listEntity: ListEntity) {
        listDao.insert(listEntity)
    }

    suspend fun deleteAll(){
        listDao.deleteAll()
    }

}