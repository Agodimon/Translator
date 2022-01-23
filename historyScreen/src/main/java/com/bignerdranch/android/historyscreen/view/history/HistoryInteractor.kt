package com.bignerdranch.android.historyscreen.view.history

import com.bignerdranch.android.core.viewmodel.Interactor
import com.bignerdranch.android.model.data.AppState
import com.bignerdranch.android.model.data.DataModel
import com.bignerdranch.android.repository.Repository
import com.bignerdranch.android.repository.RepositoryLocal


class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
