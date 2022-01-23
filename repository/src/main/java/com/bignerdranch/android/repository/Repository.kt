package com.bignerdranch.android.repository

interface Repository<T> {

    suspend fun getData(word: String): T
}
