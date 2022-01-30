package com.bignerdranch.android.repository

import com.bignerdranch.android.model.data.AppState
import com.bignerdranch.android.model.data.dto.SearchResultDto
import com.bignerdranch.android.model.data.userdata.DataModel


class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<SearchResultDto>>) :
    RepositoryLocal<List<SearchResultDto>> {

    override suspend fun getData(word: String): List<SearchResultDto> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}
