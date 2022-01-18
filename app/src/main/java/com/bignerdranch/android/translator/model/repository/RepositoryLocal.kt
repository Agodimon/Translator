package com.bignerdranch.android.translator.model.repository

import com.bignerdranch.android.translator.model.data.AppState

interface RepositoryLocal<T>:Repository<T> {
    suspend fun saveToDB(appState: AppState)
}