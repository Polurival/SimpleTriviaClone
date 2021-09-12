package com.github.polurival.stc.home.ui

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.github.polurival.stc.coreui.BaseFragment
import com.github.polurival.stc.home.R
import com.github.polurival.stc.home.databinding.HomeFragmentHomeBinding
import com.github.polurival.stc.storageapi.PreferencesManager
import com.github.polurival.stc.storageapi.StorageCoreLibApi
import com.github.polurival.stc.storageapi.USER_NAME

/**
 *
 *
 * @author Юрий Польщиков on 24.07.2021
 */
class HomeFragment : BaseFragment(R.layout.home_fragment_home) {

    private var binding: HomeFragmentHomeBinding? = null
    private var prefs: PreferencesManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = api.getCore(StorageCoreLibApi::class.java).preferencesManager
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeFragmentHomeBinding.bind(view).apply {
            val userName = prefs?.getString(USER_NAME)
            userNameTextView.text = userName?.split(" ")?.get(0)
            singleGameButton.setOnClickListener {
                locateToStartGameScreen()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        prefs = null
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
