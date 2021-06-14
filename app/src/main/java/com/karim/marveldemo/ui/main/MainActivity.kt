package com.karim.marveldemo.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.karim.marveldemo.R
import com.karim.marveldemo.databinding.ActivityMainBinding
import com.karim.marveldemo.ui.adapters.MainRecyclerAdapter
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding {
            lifecycleOwner = this@MainActivity
            adapter = MainRecyclerAdapter()
            vm = viewModel
        }
    }
}