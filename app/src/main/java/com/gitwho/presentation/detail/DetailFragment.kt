package com.gitwho.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.gitwho.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
 * [DetailFragment]
 */
@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: FragmentDetailBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        context ?: return binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        arguments?.let {
            initViews(DetailFragmentArgs.fromBundle(it).login)
        }

    }

    private fun initViews(username: String) {
        binding.fabBack.setOnClickListener { findNavController().popBackStack() }
        viewModel.getUserByUsername(username)
        initObserver()
    }

    /**
     * observing data after making an API call.
     */
    private fun initObserver() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.state.collect {
                when {
                    it.isLoading -> {
//                        binding.shimmerFrameLayout.startShimmer()
//                        binding.shimmerFrameLayout.isVisible = true
                    }
                    it.error.isNotBlank() -> {
//                        binding.shimmerFrameLayout.stopShimmer()
//                        binding.shimmerFrameLayout.isVisible = false
                    }
                    it.item != null -> {
//                        binding.shimmerFrameLayout.stopShimmer()
//                        binding.shimmerFrameLayout.isVisible = false
                        binding.user = it.item
                    }
                }
            }
        }
    }
}