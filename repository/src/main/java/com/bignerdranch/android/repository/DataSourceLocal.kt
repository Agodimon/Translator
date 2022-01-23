package com.bignerdranch.android.repository

import com.bignerdranch.android.model.data.AppState


interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}
