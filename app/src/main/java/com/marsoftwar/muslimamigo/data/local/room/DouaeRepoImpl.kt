package com.marsoftwar.muslimamigo.data.local.room

import com.marsoftwar.muslimamigo.data.repository.DouaeRepository
import kotlinx.coroutines.flow.Flow

class DouaeRepoImpl(
    private val dao: DouaeDao
) : DouaeRepository {

    override suspend fun insertDouae() = dao.insertDouae()

    override suspend fun updateDouae() = dao.update()

    override fun getAllDouaesFromDb(): Flow<List<DouaeEntity>> {
        return dao.getAllDouaesFormDb()
    }

}