package com.karim.marveldemo.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import br.com.mauker.materialsearchview.MaterialSearchView
import com.karim.marveldemo.R
import com.karim.marveldemo.databinding.ActivityMainBinding
import com.karim.marveldemo.ui.adapters.MainRecyclerAdapter
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainRecyclerAdapter = MainRecyclerAdapter()
    private val viewModel: MainViewModel by viewModels()
    private val searchView by lazy {
        binding.searchView
    }
    private val fabClearResultsBtn by lazy {
        binding.fabClearResults
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.mainToolbar)

        binding {
            lifecycleOwner = this@MainActivity
            adapter = mainRecyclerAdapter
            vm = viewModel
        }

        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.onQueryChanged(query)
                searchView.closeSearch()
                fabClearResultsBtn.visibility = View.VISIBLE
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
        fabClearResultsBtn.setOnClickListener {
            // Reset the recyclerview to the original list
            mainRecyclerAdapter.submitList(viewModel.characterData)
            mainRecyclerAdapter.notifyDataSetChanged()
            fabClearResultsBtn.visibility=View.INVISIBLE
        }
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
            searchView.clearAll()
            searchView.closeSearch()
        } else {
            super.onBackPressed()
        }
    }
}