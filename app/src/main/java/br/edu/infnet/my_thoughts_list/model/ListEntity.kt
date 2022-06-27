package br.edu.infnet.my_thoughts_list.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "thoughts_table")
data class ListEntity(
    @PrimaryKey @ColumnInfo(name = "thoughts")
    val thoughts: String
)

