package com.github.polurival.stc.main.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.polurival.stc.home.ui.HomeFragment

/**
 *
 *
 * @author Юрий Польщиков on 24.07.2021
 */
class MainViewPagerAdapter(parentFragment: Fragment) : FragmentStateAdapter(parentFragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment.create()
            1 -> HomeFragment.create() // todo
            2 -> HomeFragment.create() // todo
            else -> throw IllegalArgumentException("implement new Fragment for $position tab")
        }
    }
}