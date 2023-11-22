package com.swjjang7.reportmillie.screen.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.swjjang7.reportmillie.R
import com.swjjang7.reportmillie.databinding.ActivityMainDataBinding
import com.swjjang7.reportmillie.screen.web.WebViewActivity
import com.swjjang7.reportmillie.util.GridSpacingItemDecoration
import com.swjjang7.reportmillie.util.dp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainDataBinding
    private val viewModel: MainViewModel by viewModels()
    private val listAdapter: MainAdapter by lazy {
        MainAdapter(this, viewModel).apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainDataBinding>(
            this,
            R.layout.activity_main_data
        ).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
            it.adapter = listAdapter
        }

        initViews()
        initEvent()
    }

    private fun initViews() {
        binding.recyclerView.addItemDecoration(GridSpacingItemDecoration(10.dp, true))
    }

    private fun initEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    handleEvent(event)
                }
            }
        }
    }

    private fun handleEvent(event: Event) {
        when (event) {
            is Event.Click -> {
                startActivity(WebViewActivity.newInstance(this, event.item.url ?: ""))
            }

            Event.MoveNetworkSetting -> {
                if (checkNMoveSetting(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)) {
                    return
                }

                if (checkNMoveSetting(Settings.ACTION_WIRELESS_SETTINGS)) {
                    return
                }

                checkNMoveSetting(Settings.ACTION_WIFI_SETTINGS)
            }

            Event.Retry -> {
                viewModel.requestNewsList()
            }

            Event.None -> {}
        }
    }

    private fun checkNMoveSetting(settingsAction: String): Boolean {
        val intent = Intent(settingsAction)
        return if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
            true
        } else {
            false
        }
    }
}