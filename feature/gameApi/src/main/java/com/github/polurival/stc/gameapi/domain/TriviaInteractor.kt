package com.github.polurival.stc.gameapi.domain

import com.github.polurival.stc.gameapi.data.TriviaResponseModel
import kotlinx.coroutines.flow.Flow

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
interface TriviaInteractor {

    fun getQuestions(): Flow<TriviaResponseModel>
}