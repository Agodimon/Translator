package com.bignerdranch.android.translator.model.datasource

import com.bignerdranch.android.translator.model.data.AppState

interface DataSourceLocal<T>:DataSource<T> {
    suspend fun saveToDB(appState: AppState)
}