package com.bignerdranch.android.historyscreen.view.history

import android.os.Bundle
import com.bignerdranch.android.core.viewmodel.BaseActivity
import com.bignerdranch.android.historyscreen.databinding.ActivityHistoryBinding
import com.bignerdranch.android.model.data.AppState
import com.bignerdranch.android.model.data.userdata.DataModel

import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.getOrCreateScope
import org.koin.core.scope.Scope


class HistoryActivity : BaseActivity<AppState, HistoryInteractor>(), KoinScopeComponent {

    override val scope: Scope by getOrCreateScope()

    private lateinit var binding: ActivityHistoryBinding
    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }

        val viewModel: HistoryViewModel by inject()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }


}
