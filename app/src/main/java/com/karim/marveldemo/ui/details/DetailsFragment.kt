package com.karim.marveldemo.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.karim.marveldemo.R
import com.karim.marveldemo.data.MarvelCharacter
import com.karim.marveldemo.databinding.DetailsFragmentBinding
import com.skydoves.bindables.BindingFragment
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.transformationlayout.addTransformation
import com.skydoves.transformationlayout.onTransformationEndContainer
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailsFragment : BindingFragment<DetailsFragmentBinding>(R.layout.details_fragment) {

    companion object {
        const val TAG: String = "DetailsFragment"

        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: DetailsViewModel
    private lateinit var navController: NavController
    private lateinit var marvelCharacter: MarvelCharacter

    override fun onCreate(savedInstanceState: Bundle?) {
        val params = arguments?.getParcelable<TransformationLayout.Params>(getString(R.string.trans_params))
        onTransformationEndContainer(params)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        marvelCharacter = requireArguments().getParcelable("character")!!
        Timber.d("Details fragment: Charactername ${marvelCharacter.name}")

        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_main
        )
        return binding {
            vm = viewModel
        }.root

    }

    fun startDetailsFragment(transformationLayout: TransformationLayout, marvelCharacter: MarvelCharacter) {
        val fragment = DetailsFragment()
        val bundle = transformationLayout.getBundle(getString(R.string.trans_params))
        bundle.putParcelable(getString(R.string.character_parcelable), marvelCharacter)
        parentFragmentManager.beginTransaction().addTransformation(transformationLayout)
            .replace(R.id.nav_host_container, fragment, TAG)
            .addToBackStack(TAG).commit()
    }


}