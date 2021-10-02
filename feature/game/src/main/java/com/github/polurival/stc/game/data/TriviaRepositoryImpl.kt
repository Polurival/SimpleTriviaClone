package com.github.polurival.stc.game.data

import com.github.polurival.stc.coreapi.coroutines.CoroutineDispatchers
import com.github.polurival.stc.gameapi.data.TriviaResponseModel
import com.github.polurival.stc.gameapi.domain.TriviaModel
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
    private val triviaResponseConverter: TriviaResponseConverter,
) : TriviaRepository {

    override fun requestQuestions(amount: Int): Flow<TriviaResponseModel> {
        return flow {
            val result: TriviaResponseModel = triviaService.getQuestions(5)
            if (result.responseCode == 0) {
                val rawResult = triviaResponseConverter.convertTriviaModelToJson(result)
                preferencesManager.putString(TRIVIA_GAME, rawResult)
            }
            emit(result)
        }.flowOn(dispatchers.io)
    }

    override fun getAllQuestions(): Flow<TriviaModel> {
        return flow {
            val questions = triviaResponseConverter.convertJsonToTriviaModel(preferencesManager.getString(TRIVIA_GAME))
            emit(questions)
        }.flowOn(dispatchers.io)
    }
}
