package com.bignerdranch.android.translator

import io.reactivex.Observable

interface DataSource<T> {
    fun getData(word: String): Observable<T>
}