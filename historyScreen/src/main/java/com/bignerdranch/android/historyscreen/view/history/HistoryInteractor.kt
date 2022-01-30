package com.bignerdranch.android.historyscreen.view.history

import com.bignerdranch.android.core.viewmodel.Interactor
import com.bignerdranch.android.historyscreen.mapSearchResultToResult
import com.bignerdranch.android.model.data.AppState
import com.bignerdranch.android.model.data.dto.SearchResultDto

import com.bignerdranch.android.repository.Repository
import com.bignerdranch.android.repository.RepositoryLocal


class HistoryInteractor(
    private val repositoryRemote: Repository<List<SearchResultDto>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            mapSearchResultToResult(
                if (fromRemoteSource) {
                    repositoryRemote
                } else {
                    repositoryLocal
                }.getData(word)
            )
        )
    }
}
