package com.bignerdranch.android.translator.model.repository

import com.bignerdranch.android.translator.model.data.AppState
import com.bignerdranch.android.translator.model.data.DataModel
import com.bignerdranch.android.translator.model.datasource.DataSourceLocal

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}