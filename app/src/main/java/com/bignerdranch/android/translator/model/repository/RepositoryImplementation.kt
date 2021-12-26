package com.bignerdranch.android.translator.model.repository

import com.bignerdranch.android.translator.model.data.DataModel
import com.bignerdranch.android.translator.model.datasource.DataSource

import io.reactivex.Observable

class RepositoryImplementation(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}
