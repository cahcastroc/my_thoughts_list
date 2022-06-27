package br.edu.infnet.my_thoughts_list.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ListDao {

    @Query("SELECT * FROM thoughts_table")
    fun getAllThoughts(): Flow<List<ListEntity>>

    @Insert
     suspend fun insert(listEntity: ListEntity)

    @Query("DELETE FROM thoughts_table")
     suspend fun deleteAll()
}