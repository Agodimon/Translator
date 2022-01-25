package com.bignerdranch.android.repository

import com.bignerdranch.android.model.data.AppState
import com.bignerdranch.android.model.data.dto.SearchResultDto
import com.bignerdranch.android.repository.room.HistoryDao

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<SearchResultDto>> {

    override suspend fun getData(word: String): List<SearchResultDto> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}
