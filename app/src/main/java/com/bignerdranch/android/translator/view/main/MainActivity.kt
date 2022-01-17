package com.bignerdranch.android.translator.view.main

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.translator.R
import com.bignerdranch.android.translator.convertMeaningsToString
import com.bignerdranch.android.translator.databinding.ActivityMainBinding
import com.bignerdranch.android.translator.model.data.AppState
import com.bignerdranch.android.translator.model.data.DataModel
import com.bignerdranch.android.translator.utils.network.isOnline
import com.bignerdranch.android.translator.view.base.BaseActivity
import com.bignerdranch.android.translator.view.descriptionscreen.DescriptionActivity
import com.bignerdranch.android.translator.view.main.adapter.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: ActivityMainBinding
    override val model: MainViewModel by viewModel()

    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }

    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!,
                        convertMeaningsToString(data.meanings!!),
                        data.meanings[0].imageUrl
                    )
                )
            }
        }

    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                isNetworkAvailable = isOnline(applicationContext)
                if (isNetworkAvailable) {model.getData(searchWord, isNetworkAvailable)} else {
                    showNoInternetConnectionDialog()}
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initViews()
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                val data = appState.data
                if (data.isNullOrEmpty()) {
                    showAlertDialog(
                        getString(R.string.dialog_title_sorry),
                        getString(R.string.empty_server_response_on_success)
                    )
                } else {
                    adapter.setData(data)
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                with(binding){
                    if (appState.progress != null) {
                        progressBarHorizontal.visibility = VISIBLE
                        progressBarRound.visibility = GONE
                        progressBarHorizontal.progress = appState.progress
                    } else {
                        progressBarHorizontal.visibility = GONE
                        progressBarRound.visibility = VISIBLE
                    }
                }
            }
            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_stub), appState.error.message)
            }
        }
    }

    private fun initViewModel() {
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }

        model.subscribe().observe(this@MainActivity, { renderData(it) })
    }

    private fun initViews() {
        with(binding){
            searchFab.setOnClickListener(fabClickListener)
            mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
            mainActivityRecyclerview.adapter = adapter}
    }

    private fun showViewWorking() {
        binding.loadingFrameLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.loadingFrameLayout.visibility = VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}
