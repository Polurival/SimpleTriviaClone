package com.github.polurival.stc.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.polurival.stc.main.databinding.MainFragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 *
 *
 * @author Юрий Польщиков on 17.07.2021
 */
class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MainFragmentMainBinding.inflate(layoutInflater)
        binding.viewPager.adapter = MainViewPagerAdapter(this)
        connectViewPagerWithTabs()
        return binding.root
    }

    /**
     * https://developer.android.com/reference/com/google/android/material/tabs/TabLayout
     */
    private fun connectViewPagerWithTabs() {
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = position.toString() // todo
        }.attach()
    }
}
