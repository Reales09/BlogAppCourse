package com.reales.blogapp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.reales.blogapp.core.Result
import com.reales.blogapp.R
import com.reales.blogapp.data.remote.home.HomeScreenDataSource
import com.reales.blogapp.databinding.FragmentHomesScreenBinding
import com.reales.blogapp.domain.home.HomeScreenRepoImpl
import com.reales.blogapp.home.adapter.HomeScreenAdapter
import com.reales.blogapp.presentation.HomeScreenViewModel
import com.reales.blogapp.presentation.HomeScreenViewModelFactory


class HomesScreenFragment : Fragment(R.layout.fragment_homes_screen) {

    private lateinit var binding: FragmentHomesScreenBinding
    private val viewModel by viewModels<HomeScreenViewModel>{ HomeScreenViewModelFactory(HomeScreenRepoImpl(
        HomeScreenDataSource()
    )) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomesScreenBinding.bind(view)

        viewModel.fetchLatestPosts().observe(viewLifecycleOwner, Observer {result->
            when (result){
                is Result.Loading->{
                    binding.progressBar.visibility=View.VISIBLE

                }
                is Result.Success->{
                    binding.progressBar.visibility=View.GONE
                    binding.rvHome.adapter=HomeScreenAdapter(result.data)
                }
                is Result.Failure->{
                    binding.progressBar.visibility=View.GONE

                    Toast.makeText(requireContext(),"Ocurrio un error: ${result.exception}",Toast.LENGTH_SHORT)
                }
            }
        })


    }


}