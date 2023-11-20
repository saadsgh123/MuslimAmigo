package com.marsoftwar.muslimamigo.data.local.room

fun Douae.toDouaeEntity() : DouaeEntity {
    return DouaeEntity(
        id, title, content, category
    )
}

fun DouaeEntity.toDouae() : Douae {
    return Douae(
        id, title, content, category
    )
}