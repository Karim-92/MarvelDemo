package com.karim.marveldemo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.karim.marveldemo.R
import com.karim.marveldemo.databinding.MainFragmentBinding
import com.karim.marveldemo.ui.adapters.MainRecyclerAdapter
import com.skydoves.bindables.BindingFragment
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BindingFragment<MainFragmentBinding>(R.layout.main_fragment) {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding {
            adapter = MainRecyclerAdapter()
            vm = viewModel
        }.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
    }


}