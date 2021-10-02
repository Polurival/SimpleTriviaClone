package com.github.polurival.stc.game.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.polurival.stc.gameapi.domain.TriviaInteractor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

/**
 *
 *
 * @author Юрий Польщиков on 18.08.2021
 */
class StartGameViewModel @Inject constructor(
    triviaInteractor: TriviaInteractor,
) : ViewModel() {

    val triviaGameData = triviaInteractor.requestQuestions()
        .shareIn(viewModelScope, SharingStarted.Lazily)
}
