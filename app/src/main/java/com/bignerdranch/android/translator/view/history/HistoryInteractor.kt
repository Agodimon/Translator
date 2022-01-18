package com.bignerdranch.android.translator.view.history

import com.bignerdranch.android.translator.model.data.AppState
import com.bignerdranch.android.translator.model.data.DataModel
import com.bignerdranch.android.translator.model.repository.Repository
import com.bignerdranch.android.translator.model.repository.RepositoryLocal
import com.bignerdranch.android.translator.viewmodel.Interactor

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