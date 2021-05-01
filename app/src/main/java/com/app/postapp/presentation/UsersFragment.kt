package com.app.postapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.postapp.databinding.FragmentUsersBinding
import com.app.postapp.presentation.adapter.UserAdapter

class UsersFragment : BaseFragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding: FragmentUsersBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.usersLiveData.observe(viewLifecycleOwner, Observer { users ->
            users ?: return@Observer
            with(binding.userRecyclerView) {
                setHasFixedSize(true)
                setItemViewCacheSize(20)
                layoutManager = LinearLayoutManager(
                    binding.root.context,
                    RecyclerView.VERTICAL,
                    false
                )
                adapter = UserAdapter(users) {
                    //Show
                }
            }
        })

        //viewModel.getUsersWithEiter()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}