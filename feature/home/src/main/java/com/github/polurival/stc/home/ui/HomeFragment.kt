package com.github.polurival.stc.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.github.polurival.stc.home.databinding.HomeFragmentHomeBinding
import com.github.polurival.stc.storage.PreferencesManager
import com.github.polurival.stc.storage.PreferencesManager.Companion.USER_NAME

/**
 *
 *
 * @author Юрий Польщиков on 24.07.2021
 */
class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentHomeBinding
    private lateinit var prefs: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = PreferencesManager.create(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = HomeFragmentHomeBinding.inflate(layoutInflater)
        val userName = prefs.getString(USER_NAME)
        binding.userNameTextView.text = userName?.split(" ")?.get(0)
        binding.singleGameButton.setOnClickListener {
            locateToStartGameScreen()
        }
        return binding.root
    }

    /**
     * todo move to Router class
     */
    private fun locateToStartGameScreen() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://com.github.polurival.stc/app_nav_fragment_start_game".toUri())
            .build()
        findNavController().apply {
            navigate(request)
        }
    }

    companion object {
        fun create(): HomeFragment {
            return HomeFragment()
        }
    }
}
