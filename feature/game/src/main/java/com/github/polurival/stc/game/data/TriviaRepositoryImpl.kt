package com.github.polurival.stc.game.data

import com.github.polurival.stc.coreapi.coroutines.CoroutineDispatchers
import com.github.polurival.stc.gameapi.data.TriviaResponseModel
import com.github.polurival.stc.storageapi.PreferencesManager
import com.github.polurival.stc.storageapi.TRIVIA_GAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
class TriviaRepositoryImpl @Inject constructor(
    private val dispatchers: CoroutineDispatchers,
    private val triviaService: TriviaService,
    private val preferencesManager: PreferencesManager,
) : TriviaRepository {

    override fun getQuestions(amount: Int): Flow<TriviaResponseModel> {
        return flow {
            val result: TriviaResponseModel = triviaService.getQuestions(5)
            preferencesManager.putString(TRIVIA_GAME, result.toString())
            emit(result)
        }.flowOn(dispatchers.io)
    }
}
