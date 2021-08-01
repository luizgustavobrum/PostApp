package com.app.postapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.postapp.R
import com.app.postapp.databinding.FragmentUsersBinding
import com.app.postapp.domain.model.User
import com.app.postapp.presentation.adapter.UserAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding: FragmentUsersBinding get() = _binding!!
    private val viewModel: DataViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swiperefresh.setOnRefreshListener {
            viewModel.getUsers()
            binding.swiperefresh.isRefreshing = false
        }

        viewModel.uiUsersState.asLiveData().observe(viewLifecycleOwner, Observer { states ->
            when (states) {
                is UiState.Initial -> Unit
                is UiState.Loading -> {
                    binding.progressCircular.visibility = VISIBLE
                    binding.userRecyclerView.visibility = GONE
                }
                is UiState.Success -> {
                    binding.progressCircular.visibility = GONE
                    binding.userRecyclerView.visibility = VISIBLE
                    createUsersRecyclerView(states.success)
                }
                is UiState.Error -> {

                }
            }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun createUsersRecyclerView(users: List<User>) {
        with(binding.userRecyclerView) {
            setHasFixedSize(true)
            setItemViewCacheSize(50)
            layoutManager =
                LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
            adapter = UserAdapter(users) {
                //Show
            }
        }

    }
}