package com.gitwho.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gitwho.common.AppUtils
import com.gitwho.common.Constants.EMPTY_STRING
import com.gitwho.common.OnRecyclerItemClickCallback
import com.gitwho.databinding.FragmentHomeBinding
import com.gitwho.domain.model.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
 * [HomeFragment]
 */
@AndroidEntryPoint
class HomeFragment : Fragment(), OnRecyclerItemClickCallback<User> {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        context ?: return binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        initSearch()
        setupRecyclerView()
    }

    private fun initSearch() {
        binding.svUsers.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.getUserByQuery(it)
                } ?: run {
                    AppUtils.hideKeyboard(requireView())
                    binding.tvSearchPh.isVisible = true
                    binding.rvUser.isVisible = false
                }
                return false
            }
        })
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter(this)
        val linearLayoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )
        binding.rvUser.layoutManager =
            linearLayoutManager
        binding.rvUser.adapter = userAdapter
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
                        binding.shimmerFrameLayout.startShimmer()
                        binding.shimmerFrameLayout.isVisible = true
                        binding.tvSearchPh.isVisible = false
                    }
                    it.error.isNotBlank() -> {
                        binding.shimmerFrameLayout.stopShimmer()
                        binding.shimmerFrameLayout.isVisible = false
                        binding.tvSearchPh.isVisible = true
                        binding.rvUser.isVisible = false
                    }
                    it.data.isNotEmpty() -> {
                        binding.shimmerFrameLayout.stopShimmer()
                        binding.shimmerFrameLayout.isVisible = false
                        binding.tvSearchPh.isVisible = false
                        binding.rvUser.isVisible = true
                        userAdapter.submitList(it.data)
                    }
                }
            }
        }
    }

    override fun onRecyclerItemClicked(position: Int, view: View, data: User) {
        AppUtils.hideKeyboard(requireView())
        findNavController().navigate(
            HomeFragmentDirections.actionHomeToDetailFragment(
                data.login ?: EMPTY_STRING
            )
        )
    }
}