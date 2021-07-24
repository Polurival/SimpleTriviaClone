package com.github.polurival.stc.game.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.polurival.stc.game.databinding.GameFragmentGameBinding

/**
 *
 *
 * @author Юрий Польщиков on 24.07.2021
 */
class GameFragment: Fragment() {

    private lateinit var binding: GameFragmentGameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = GameFragmentGameBinding.inflate(inflater)
        return binding.root
    }
}
