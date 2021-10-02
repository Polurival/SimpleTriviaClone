package com.github.polurival.stc.game.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 *
 *
 * @author Юрий Польщиков on 02.10.2021
 */
class GameViewModelFactory @Inject constructor(
    private val provider: Provider<GameViewModel>
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = provider.get() as T
}