package com.example.mealzapp.ui.mealz

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.domain.entitey.CategoriesResponse
import com.example.domain.entitey.Category
import com.example.mealzapp.R
import com.example.mealzapp.databinding.ActivityMainBinding
import com.example.mealzapp.utils.NetworkState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MealzViewModel by viewModels()

    @Inject
    lateinit var mealzAdapter: MealzAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setup()
        data()
        observe()

    }

    private fun setup() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        binding.rv.adapter = mealzAdapter

    }

    private fun data() {
        viewModel.getMealz()
    }

    private fun observe() {
        lifecycleScope.launchWhenStarted {
            viewModel.getMealzStateFlow.collect {
                when (it) {
                    is NetworkState.Idle -> {
                        return@collect
                    }
                    is NetworkState.Loading -> {
                        visProgress(true)
                    }
                    is NetworkState.Error -> {
                        visProgress(false)
                        it.handleErrors(this@MainActivity)
                    }
                    is NetworkState.Result<*> -> {
                        visProgress(false)
                        handleResult(it.response)
                    }
                }

            }
        }
    }

    private fun <T> handleResult(response: T) {

        when (response) {
            is CategoriesResponse -> ui(response.categories)
        }

    }

    private fun ui(categories: List<Category>) {
        mealzAdapter.submitList(categories)
    }

    private fun visProgress(state: Boolean) {
        binding.progress.isVisible = state
    }
}