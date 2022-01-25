package com.bignerdranch.android.repository

import com.bignerdranch.android.model.data.AppState


interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}
