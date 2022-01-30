package com.bignerdranch.android.translator.view.main

import com.bignerdranch.android.core.viewmodel.Interactor
import com.bignerdranch.android.historyscreen.mapSearchResultToResult
import com.bignerdranch.android.model.data.AppState
import com.bignerdranch.android.model.data.dto.SearchResultDto
import com.bignerdranch.android.model.data.userdata.DataModel

import com.bignerdranch.android.repository.Repository
import com.bignerdranch.android.repository.RepositoryLocal


class MainInteractor(
    private val repositoryRemote: Repository<List<SearchResultDto>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDto>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(mapSearchResultToResult(repositoryRemote.getData(word)))
            repositoryLocal.saveToDB(appState)
        } else {
            appState = AppState.Success(mapSearchResultToResult(repositoryLocal.getData(word)))
        }
        return appState
    }
}
