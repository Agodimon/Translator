package com.bignerdranch.android.translator.view.base

import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.translator.model.data.AppState
import com.bignerdranch.android.translator.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)
}
