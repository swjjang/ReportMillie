package com.swjjang7.reportmillie.screen.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.swjjang7.reportmillie.R
import com.swjjang7.reportmillie.databinding.ActivityMainDataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainDataBinding
    private val viewModel: MainViewModel by viewModels()
    private val listAdapter: MainAdapter by lazy {
        MainAdapter(this).apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_data)

        binding = DataBindingUtil.setContentView<ActivityMainDataBinding>(
            this,
            R.layout.activity_main_data
        ).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
            it.adapter = listAdapter
        }
    }
}