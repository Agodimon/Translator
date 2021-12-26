package com.bignerdranch.android.translator.view.base

import com.bignerdranch.android.translator.model.data.AppState

interface View {

    fun renderData(appState: AppState)

}
