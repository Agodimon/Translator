package com.bignerdranch.android.translator.model.repository

import com.bignerdranch.android.translator.model.data.DataModel
import com.bignerdranch.android.translator.model.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}
