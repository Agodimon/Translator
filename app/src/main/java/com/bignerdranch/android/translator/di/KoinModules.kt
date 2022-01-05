package com.bignerdranch.android.translator.di

import com.bignerdranch.android.translator.model.data.DataModel
import com.bignerdranch.android.translator.model.datasource.RetrofitImplementation
import com.bignerdranch.android.translator.model.datasource.RoomDataBaseImplementation
import com.bignerdranch.android.translator.model.repository.Repository
import com.bignerdranch.android.translator.model.repository.RepositoryImplementation
import com.bignerdranch.android.translator.view.main.MainInteractor
import com.bignerdranch.android.translator.view.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(
            RoomDataBaseImplementation()
        )
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}