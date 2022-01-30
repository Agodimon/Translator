package com.bignerdranch.android.repository

import com.bignerdranch.android.model.data.dto.SearchResultDto
import com.bignerdranch.android.model.data.userdata.DataModel


class RepositoryImplementation(private val dataSource: DataSource<List<SearchResultDto>>) :
    Repository<List<SearchResultDto>> {

    override suspend fun getData(word: String): List<SearchResultDto> {
        return dataSource.getData(word)
    }
}
