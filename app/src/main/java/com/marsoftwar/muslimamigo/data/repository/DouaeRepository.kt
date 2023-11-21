package com.marsoftwar.muslimamigo.data.repository

import com.marsoftwar.muslimamigo.data.local.room.Douae
import com.marsoftwar.muslimamigo.data.local.room.DouaeEntity
import kotlinx.coroutines.flow.Flow

interface DouaeRepository {

    suspend fun insertDouae(douae: Douae)

    suspend fun updateDouae(douae: Douae)

    fun getAllDouaesFromDb() : Flow<List<Douae>>

    suspend fun deleteAllDouaes()

}