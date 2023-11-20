package com.marsoftwar.muslimamigo.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DouaeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDouae()

    @Update
    suspend fun update()

    @Query("SELECT * FROM douae")
    fun getAllDouaesFormDb() : Flow<List<DouaeEntity>>

}