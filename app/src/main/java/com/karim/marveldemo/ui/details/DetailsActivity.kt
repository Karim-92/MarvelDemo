package com.karim.marveldemo.ui.details

import android.os.Bundle
import androidx.activity.viewModels
import com.karim.marveldemo.PARCELABLE_KEY
import com.karim.marveldemo.R
import com.karim.marveldemo.data.MarvelCharacter
import com.karim.marveldemo.databinding.ActivityDetailsBinding
import com.karim.marveldemo.extensions.onTransformationEndContainerApplyParams
import com.karim.marveldemo.ui.adapters.GenericAdapter
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import com.skydoves.bundler.intentOf
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsActivity : BindingActivity<ActivityDetailsBinding>(R.layout.activity_details) {

    @Inject
    lateinit var detailViewModelFactory: DetailsViewModel.AssistedFactory

    val viewModel: DetailsViewModel by viewModels {
        DetailsViewModel.provideFactory(detailViewModelFactory, marvelCharacterItem.id)
    }

    private val marvelCharacterItem: MarvelCharacter by bundleNonNull(PARCELABLE_KEY)

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationEndContainerApplyParams()
        super.onCreate(savedInstanceState)
        binding {
            lifecycleOwner = this@DetailsActivity
            marvelCharacter = marvelCharacterItem
            comicsAdapter = GenericAdapter()
            eventsAdapter = GenericAdapter()
            storiesAdapter = GenericAdapter()
            seriesAdapter = GenericAdapter()
            vm = viewModel
        }
    }

    companion object {
        fun startActivity(
            transformationLayout: TransformationLayout,
            marvelCharacter: MarvelCharacter
        ) =
            transformationLayout.context.intentOf<DetailsActivity> {
                putExtra(PARCELABLE_KEY to marvelCharacter)
                TransformationCompat.startActivity(transformationLayout, intent)
            }
    }

    override fun onBackPressed() {
        finish()
    }
}