package com.bignerdranch.android.translator.model.datasource

import com.bignerdranch.android.translator.model.data.DataModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<DataModel>>
}
