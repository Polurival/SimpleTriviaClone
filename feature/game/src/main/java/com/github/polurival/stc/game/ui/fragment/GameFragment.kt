package com.github.polurival.stc.game.ui.fragment

import android.os.Bundle
import android.view.View
import com.github.polurival.stc.coreui.BaseFragment
import com.github.polurival.stc.game.R
import com.github.polurival.stc.game.databinding.GameFragmentGameBinding
import com.github.polurival.stc.storageapi.PreferencesManager
import com.github.polurival.stc.storageapi.StorageCoreLibApi
import com.github.polurival.stc.storageapi.TRIVIA_GAME

/**
 *
 *
 * @author Юрий Польщиков on 24.07.2021
 */
class GameFragment : BaseFragment(R.layout.game_fragment_game) {

    //todo move to viewModel, interactor or repository
    private var prefs: PreferencesManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = api.getCore(StorageCoreLibApi::class.java).preferencesManager
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = GameFragmentGameBinding.bind(view)

        // todo add kotlin serialization model -> json -> model
        binding.testView.text = prefs?.getString(TRIVIA_GAME)
    }

    override fun onDestroy() {
        super.onDestroy()
        prefs = null
    }
}
