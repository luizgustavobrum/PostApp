package com.app.postapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.postapp.R
import com.app.postapp.databinding.FragmentPostsBinding
import com.app.postapp.domain.model.Post
import com.app.postapp.presentation.adapter.PostAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DataViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swiperefresh.setOnRefreshListener {
            viewModel.getPosts()
            binding.swiperefresh.isRefreshing = false
        }

        viewModel.uiPostsState.asLiveData().observe(viewLifecycleOwner, Observer { states ->
            when (states) {
                is UiState.Initial -> Unit
                is UiState.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                    binding.postRecyclerView.visibility = View.GONE
                }
                is UiState.Success -> {
                    binding.progressCircular.visibility = View.GONE
                    binding.postRecyclerView.visibility = View.VISIBLE
                    createPostsRecyclerView(states.success)
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

    private fun createPostsRecyclerView(posts: List<Post>) {
        with(binding.postRecyclerView) {
            setHasFixedSize(true)
            setItemViewCacheSize(50)
            layoutManager =
                LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
            adapter = PostAdapter(posts) {
                //Show
            }
        }
    }

}