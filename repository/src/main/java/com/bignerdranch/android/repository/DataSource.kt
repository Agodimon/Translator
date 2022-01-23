package com.bignerdranch.android.repository

interface DataSource<T> {

    suspend fun getData(word: String): T
}
