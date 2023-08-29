package com.example.githubapisubmission.component

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.githubapisubmission.adapter.FollowListAdapter
import com.example.githubapisubmission.data.viewmodel.SharedViewModel
import com.example.githubapisubmission.data.response.FollowListResponse
import com.example.githubapisubmission.databinding.FragmentFollowingBinding


class FollowingFragment : Fragment() {
    private lateinit var binding:FragmentFollowingBinding
    private val viewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowingBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.readResponseBodyFollowingList.observe(viewLifecycleOwner){
            setupRecyclerView(it)
        }
    }

    private fun setupRecyclerView(it: List<FollowListResponse?>) {
        //binding.recyclerViewFragmentFollowing.isNestedScrollingEnabled = false
        binding.recyclerViewFragmentFollowing.adapter = FollowListAdapter(it)
    }
}