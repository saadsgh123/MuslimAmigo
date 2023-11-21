package com.marsoftwar.muslimamigo.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.marsoftwar.muslimamigo.data.local.room.Douae
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseSource@Inject constructor() {

    private val firestore = FirebaseFirestore.getInstance()
    private val collection = firestore.collection("douae")

    suspend fun getAllDouaes() : List<Douae>{
        return try {
            collection.get().await().map { doc ->
                doc.toObject(Douae::class.java)
            }
        } catch (e: FirebaseFirestoreException){
            emptyList()
        }
    }

}