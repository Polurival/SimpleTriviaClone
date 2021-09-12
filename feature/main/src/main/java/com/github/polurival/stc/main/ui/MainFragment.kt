package com.github.polurival.stc.main.ui

import android.os.Bundle
import android.view.View
import com.github.polurival.stc.coreui.BaseFragment
import com.github.polurival.stc.main.R
import com.github.polurival.stc.main.databinding.MainFragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Фрагмент, являющийся хостом для содержания фрагментов-вкладок фичей или настроек
 *
 * @author Юрий Польщиков on 17.07.2021
 */
class MainFragment : BaseFragment(R.layout.main_fragment_main) {

    private var binding: MainFragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = MainFragmentMainBinding.bind(view).apply {
            viewPager.adapter = MainViewPagerAdapter(this@MainFragment)
        }
        connectViewPagerWithTabs()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    /**
     * https://developer.android.com/reference/com/google/android/material/tabs/TabLayout
     */
    private fun connectViewPagerWithTabs() {
        binding?.let {
            TabLayoutMediator(it.tabs, it.viewPager) { tab, position ->
                tab.text = position.toString() // todo
            }.attach()
        }
    }
}
