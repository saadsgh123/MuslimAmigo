package com.marsoftwar.muslimamigo.data.repository

import com.marsoftwar.muslimamigo.data.local.room.DouaeEntity
import kotlinx.coroutines.flow.Flow

interface DouaeRepository {

    suspend fun insertDouae()

    suspend fun updateDouae()

    fun getAllDouaesFromDb() : Flow<List<DouaeEntity>>

}