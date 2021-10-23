package com.github.polurival.stc.gameapi.domain

import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
interface TriviaInteractor {

    fun requestQuestions(): Flow<Unit>

    fun getQuestions(): Flow<TriviaModel>
}
