package com.marsoftwar.muslimamigo.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DouaeEntity::class],
    version = 1
)
 abstract class Database : RoomDatabase() {

     abstract val dao : DouaeDao

}