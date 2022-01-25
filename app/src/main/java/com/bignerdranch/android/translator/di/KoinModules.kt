package com.bignerdranch.android.translator.di

import androidx.room.Room
import com.bignerdranch.android.historyscreen.view.history.HistoryInteractor
import com.bignerdranch.android.historyscreen.view.history.HistoryViewModel
import com.bignerdranch.android.model.data.DataModel
import com.bignerdranch.android.repository.*
import com.bignerdranch.android.repository.room.HistoryDataBase

import com.bignerdranch.android.translator.view.main.MainInteractor
import com.bignerdranch.android.translator.view.main.MainViewModel
import org.koin.dsl.module


val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}