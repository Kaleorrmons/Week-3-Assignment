package com.example.week_3_assignment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.week_3_assignment.Fragments.ClassicFragment
import com.example.week_3_assignment.Fragments.PopFragment
import com.example.week_3_assignment.Fragments.RockFragment

class TabPageAdapter(activity: FragmentActivity, private val tabCount: Int) : FragmentStateAdapter(activity)
{

    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment
    {
       return when(position)
       {
           0 -> RockFragment()
           1 -> PopFragment()
           2 -> ClassicFragment()
           else -> RockFragment()
       }
    }

}