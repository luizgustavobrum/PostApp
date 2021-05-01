package com.app.postapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.postapp.repository.DataRepository

open class BaseFragment : Fragment() {
    val viewModel by activityViewModels<DataViewModel>{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return DataViewModel(DataRepository()) as T
            }
        }
    }
}
