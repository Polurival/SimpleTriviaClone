package com.github.polurival.stc.game.data

import com.github.polurival.stc.gameapi.domain.TriviaModel
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
interface TriviaRepository {

    fun requestQuestions(amount: Int): Flow<Unit>

    fun getAllQuestions(): Flow<TriviaModel>
}

const val QUIZ_QUESTION_COUNT = 5
