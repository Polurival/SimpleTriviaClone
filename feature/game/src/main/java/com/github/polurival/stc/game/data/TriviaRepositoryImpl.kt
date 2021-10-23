package com.github.polurival.stc.game.data

import android.content.Context
import com.github.polurival.stc.androidutil.isNetworkAvailable
import com.github.polurival.stc.coreapi.context.ApplicationContext
import com.github.polurival.stc.coreapi.coroutines.CoroutineDispatchers
import com.github.polurival.stc.gameapi.data.TriviaResponseModel
import com.github.polurival.stc.gameapi.domain.TriviaModel
import com.github.polurival.stc.storageapi.PreferencesManager
import com.github.polurival.stc.storageapi.TRIVIA_GAME
import com.github.polurival.stc.storageapi.data.TriviaDao
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
    @ApplicationContext val context: Context,
    private val dispatchers: CoroutineDispatchers,
    private val triviaService: TriviaService,
    private val preferencesManager: PreferencesManager,
    private val triviaDao: TriviaDao,
    private val triviaResponseConverter: TriviaResponseConverter,
) : TriviaRepository {

    override fun requestQuestions(amount: Int): Flow<Unit> {
        return flow {
            if (isNetworkAvailable(context)) {
                val result: TriviaResponseModel = triviaService.getQuestions(QUIZ_QUESTION_COUNT)
                if (result.responseCode == 0) {
                    // сохраняем для текущей онлайн игры:
                    val rawResult = triviaResponseConverter.convertTriviaModelToJson(result)
                    preferencesManager.putString(TRIVIA_GAME, rawResult)

                    // сохряняем в БД для возможности оффлайн игры:
                    val entities = triviaResponseConverter.convertTriviaModelToEntity(result)
                    triviaDao.insertNewTriviaModels(*entities)
                }
            } else {
                // достаем из БД для оффлайн игры:
                val gameModel = triviaDao.getTriviaModelsForOfflineGame()

                // сохраняем для текущей онлайн игры:
                val rawOfflineResult = triviaResponseConverter.convertEntitiesToJson(gameModel.entities)
                preferencesManager.putString(TRIVIA_GAME, rawOfflineResult)
            }
            emit(Unit)
        }.flowOn(dispatchers.io)
    }

    override fun getAllQuestions(): Flow<TriviaModel> {
        return flow {
            val questions = triviaResponseConverter.convertJsonToTriviaModel(preferencesManager.getString(TRIVIA_GAME))
            emit(questions)
        }.flowOn(dispatchers.io)
    }
}
