package com.example.githubapisubmission.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubapisubmission.component.FollowFragment


class SectionsPagerAdapter(activity:AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment:Fragment? = null
        when (position){
            0 -> fragment = FollowFragment(1)
            1 -> fragment = FollowFragment(2)
        }
        return fragment as Fragment
    }


}