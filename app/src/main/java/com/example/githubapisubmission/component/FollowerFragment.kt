package com.example.githubapisubmission.component

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.githubapisubmission.adapter.FollowListAdapter
import com.example.githubapisubmission.data.viewmodel.SharedViewModel
import com.example.githubapisubmission.data.response.FollowListResponse
import com.example.githubapisubmission.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentFollowerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("FOLLOWER", "INSIDE onCreateView")
        binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.readResponseBodyFollowerList.observe(viewLifecycleOwner){
            setupRecyclerView(it)
        }
    }

    private fun setupRecyclerView(list: List<FollowListResponse?>) {
        binding.recyclerViewFragmentFollower.adapter = FollowListAdapter(list)
    }
}