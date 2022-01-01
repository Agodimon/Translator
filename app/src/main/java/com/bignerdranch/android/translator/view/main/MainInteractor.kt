package com.bignerdranch.android.translator.view.main


import com.bignerdranch.android.translator.di.NAME_LOCAL
import com.bignerdranch.android.translator.di.NAME_REMOTE
import com.bignerdranch.android.translator.model.data.AppState
import com.bignerdranch.android.translator.model.data.DataModel
import com.bignerdranch.android.translator.model.repository.Repository
import com.bignerdranch.android.translator.viewmodel.Interactor
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val repositoryRemote: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val repositoryLocal: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            repositoryRemote
        } else {
            repositoryLocal
        }.getData(word).map { AppState.Success(it) }
    }
}
