package com.marsoftwar.muslimamigo.data.local.room

import com.marsoftwar.muslimamigo.data.repository.DouaeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DouaeRepoImpl(
    private val dao: DouaeDao
) : DouaeRepository {
    override suspend fun insertDouae(douae: Douae) {
        dao.insertDouae(douae.toDouaeEntity())
    }

    override suspend fun updateDouae(douae: Douae) {
        dao.update(douae.toDouaeEntity())
    }

    override fun getAllDouaesFromDb(): Flow<List<Douae>> {
        return dao.getAllDouaesFormDb().map {orders->
            orders.map {
                it.toDouae()
            }
        }
    }

    override suspend fun deleteAllDouaes() {
        dao.deleteAllDouaes()
    }


}