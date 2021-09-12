package com.github.polurival.stc.game.data

import com.github.polurival.stc.gameapi.data.TriviaResponseModel
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
interface TriviaRepository {

    fun getQuestions(amount: Int): Flow<TriviaResponseModel>
}
