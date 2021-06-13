package com.karim.marveldemo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.karim.marveldemo.R
import com.karim.marveldemo.data.CharacterData
import com.karim.marveldemo.databinding.MainFragmentBinding
import com.karim.marveldemo.ui.details.DetailsFragment
import com.skydoves.bindables.BindingFragment
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.transformationlayout.addTransformation
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BindingFragment<MainFragmentBinding>(R.layout.main_fragment) {

    val viewModel: MainViewModel by viewModels()

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

    fun startDetailsFragment(transformationLayout: TransformationLayout, character: CharacterData) {
        val fragment = DetailsFragment()
        val bundle = transformationLayout.getBundle(getString(R.string.trans_params))
        bundle.putParcelable(getString(R.string.character_parcelable), character)
        parentFragmentManager.beginTransaction().addTransformation(transformationLayout)
            .replace(R.id.nav_host_container, fragment, DetailsFragment.TAG)
            .addToBackStack(DetailsFragment.TAG).commit()
    }

}