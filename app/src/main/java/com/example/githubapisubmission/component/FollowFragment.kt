package com.example.githubapisubmission.component

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.githubapisubmission.adapter.FollowListAdapter
import com.example.githubapisubmission.data.viewmodel.FollowerViewModel
import com.example.githubapisubmission.data.viewmodel.FollowingViewModel
import com.example.githubapisubmission.databinding.FragmentFollowBinding


class FollowFragment(private val fragmentID: Int) : Fragment() {
    private lateinit var binding: FragmentFollowBinding
    private val followerViewModel: FollowerViewModel by activityViewModels()
    private val followingViewModel: FollowingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }


    private fun setupRecyclerView() {
        when (fragmentID) {
            1 -> followerViewModel.readResponseBodyFollowerList.observe(viewLifecycleOwner) {
                binding.recyclerViewFragmentFollow.adapter = FollowListAdapter(it)
            }

            2 -> followingViewModel.readResponseBodyFollowingList.observe(viewLifecycleOwner) {
                binding.recyclerViewFragmentFollow.adapter = FollowListAdapter(it)
            }
        }

    }
}