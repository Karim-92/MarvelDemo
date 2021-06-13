package com.karim.marveldemo.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.karim.marveldemo.R
import com.karim.marveldemo.data.CharacterData
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.transformationlayout.onTransformationEndContainer
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    companion object {
        const val TAG: String = "DetailsFragment"

        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: DetailsViewModel
    private lateinit var navController: NavController
    private lateinit var characterData: CharacterData

    override fun onCreate(savedInstanceState: Bundle?) {
        val params = arguments?.getParcelable<TransformationLayout.Params>(getString(R.string.trans_params))
        onTransformationEndContainer(params)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        characterData = requireArguments().getParcelable("character")!!
        Timber.d("Details fragment: Charactername ${characterData.name}")

        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_main
        )
        return inflater.inflate(R.layout.details_fragment, container, false)

    }


}