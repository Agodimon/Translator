package com.bignerdranch.android.translator.model.datasource

import com.bignerdranch.android.translator.convertDataModelSuccessToEntity
import com.bignerdranch.android.translator.mapHistoryEntityToSearchResult
import com.bignerdranch.android.translator.model.data.AppState
import com.bignerdranch.android.translator.model.data.DataModel
import com.bignerdranch.android.translator.room.HistoryDao


class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}
