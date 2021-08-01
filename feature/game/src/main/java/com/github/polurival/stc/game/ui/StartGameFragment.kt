package com.github.polurival.stc.game.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import com.github.polurival.stc.androidutil.findNavControllerSafely
import com.github.polurival.stc.game.databinding.GameFragmentStartGameBinding

/**
 *
 *
 * @author Юрий Польщиков on 24.07.2021
 */
class StartGameFragment : Fragment() {

    private lateinit var binding: GameFragmentStartGameBinding
    private val locateToMainScreenCallback = { locateToGameScreen() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = GameFragmentStartGameBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(locateToMainScreenCallback, 2000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Handler(Looper.getMainLooper()).removeCallbacks(locateToMainScreenCallback)
    }

    /**
     * todo move to Router class
     */
    private fun locateToGameScreen() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://com.github.polurival.stc/app_nav_fragment_game".toUri())
            .build()
        findNavControllerSafely()?.apply {
            popBackStack()
            navigate(request)
        }
    }
}
