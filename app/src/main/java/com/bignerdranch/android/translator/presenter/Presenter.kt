package com.bignerdranch.android.translator.presenter

import com.bignerdranch.android.translator.model.data.AppState
import com.bignerdranch.android.translator.view.base.View

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}
