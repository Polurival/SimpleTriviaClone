package com.github.polurival.stc.game.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.polurival.stc.gameapi.domain.Quiz
import com.github.polurival.stc.gameapi.domain.TriviaInteractor
import com.github.polurival.stc.gameapi.domain.TriviaModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 *
 *
 * @author Юрий Польщиков on 18.08.2021
 */
class GameViewModel @Inject constructor(
    triviaInteractor: TriviaInteractor,
) : ViewModel() {

    private var game: TriviaModel? = null
    private var currentQuizIndex = 0
    private var correctAnswersCount = 0

    private val startGame = MutableStateFlow<State>(State.Init)
    val startGameFlow: Flow<State> = startGame

    init {
        triviaInteractor.getQuestions()
            .onEach {
                game = it
                startGame.emit(State.StartGame(getCurrentQuiz()))
            }
            .launchIn(viewModelScope)
    }

    suspend fun checkAnswer(answer: String) {
        getCurrentQuiz()?.let {
            val chosenAnswerIndex = it.allAnswers.indexOf(answer)
            if (it.correctAnswer == answer) {
                correctAnswersCount++
                startGame.emit(State.CorrectChoose(chosenAnswerIndex))
            } else {
                startGame.emit(State.IncorrectChoose(chosenAnswerIndex, it.allAnswers.indexOf(it.correctAnswer)))
            }

            delay(2000L)
            currentQuizIndex++
            if (isGameOver()) {
                startGame.emit(State.GameOver(correctAnswersCount))
            } else {
                startGame.emit(State.StartGame(getCurrentQuiz()))
            }
        }
    }

    private fun getCurrentQuiz(): Quiz? {
        return game?.quizes?.get(currentQuizIndex)
    }

    private fun isGameOver(): Boolean {
        return game?.quizes?.size == currentQuizIndex
    }

    sealed class State {
        object Init : State()
        class StartGame(val quiz: Quiz?) : State()
        class CorrectChoose(val correctIndex: Int) : State()
        class IncorrectChoose(val incorrectIndex: Int, val correctIndex: Int) : State()
        class GameOver(val correctAnswersCount: Int) : State()
    }
}
