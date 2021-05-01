package com.app.postapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.postapp.databinding.FragmentPostsBinding
import com.app.postapp.network.Post
import com.app.postapp.presentation.adapter.PostAdapter

class PostsFragment : BaseFragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.postsLiveData.observe(viewLifecycleOwner, Observer { posts ->
            with(binding.postRecyclerView) {
                setHasFixedSize(true)
                setItemViewCacheSize(50)
                layoutManager =
                    LinearLayoutManager(binding.root.context, RecyclerView.VERTICAL, false)
                adapter = PostAdapter(posts) {
                    //Show
                }
            }
        })

        //viewModel.getPostsWithEither()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}