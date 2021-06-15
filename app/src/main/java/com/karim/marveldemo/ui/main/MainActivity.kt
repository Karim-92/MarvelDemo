package com.karim.marveldemo.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import br.com.mauker.materialsearchview.MaterialSearchView
import com.karim.marveldemo.R
import com.karim.marveldemo.databinding.ActivityMainBinding
import com.karim.marveldemo.ui.adapters.MainRecyclerAdapter
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    val viewModel: MainViewModel by viewModels()
    val searchView: MaterialSearchView by lazy {
        binding.searchView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.mainToolbar)

        binding {
            lifecycleOwner = this@MainActivity
            adapter = MainRecyclerAdapter()
            vm = viewModel
        }

        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Timber.d("Query Submitted")
                searchView.closeSearch()
                return viewModel.onQuery(query)
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Timber.d("Query Changed")
                viewModel.onQuery(newText)
                return true
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_view_search->{
                searchView.openSearch()
            }
        }
        return true
    }

    override fun onBackPressed() {
        if (searchView.isOpen) {
            // Close the search on the back button press.
            searchView.closeSearch()
        } else {
            super.onBackPressed()
        }
    }
}