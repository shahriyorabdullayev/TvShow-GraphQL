package com.shahriyor.android_imperativ_graphql.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.shahriyor.android_imperativ_graphql.ui.adapter.MainAdapter
import com.shahriyor.android_imperativ_graphql.databinding.ActivityMainBinding
import com.shahriyor.android_imperativ_graphql.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { MainAdapter() }
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvHome.adapter = adapter
        viewModel.apiMovies()

        observeViewModel()

    }

    private fun observeViewModel() {

        with(viewModel) {
            isLoading.observe(this@MainActivity) { isLoading ->
                if (isLoading) {
                    binding.pbLoading.visibility = View.VISIBLE
                } else {
                    binding.pbLoading.visibility = View.GONE
                }
            }

            error.observe(this@MainActivity) {
                Log.d("@@@", "observeViewModel: $it")
            }

            movies.observe(this@MainActivity) {
                adapter.submitList(it)
            }
        }


    }


}