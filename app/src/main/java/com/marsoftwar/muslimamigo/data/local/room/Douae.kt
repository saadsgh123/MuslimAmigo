package com.marsoftwar.muslimamigo.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Douae(
    val id:Int? = null,
    val title:String = "",
    val content:String = "",
    val category: String = ""
)

@Entity(tableName = "Douae")
data class DouaeEntity(
    @PrimaryKey val id:Int? = null,
    val title: String,
    val content: String,
    val category: String
)