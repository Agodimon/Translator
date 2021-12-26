package com.bignerdranch.android.translator

import io.reactivex.Observable

interface Repository<T> {
    fun getData(word: String): Observable<T>
}