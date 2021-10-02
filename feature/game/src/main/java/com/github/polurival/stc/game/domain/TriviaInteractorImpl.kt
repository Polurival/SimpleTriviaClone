package com.github.polurival.stc.game.domain

import com.github.polurival.stc.game.data.TriviaRepository
import com.github.polurival.stc.gameapi.data.TriviaResponseModel
import com.github.polurival.stc.gameapi.domain.TriviaInteractor
import com.github.polurival.stc.gameapi.domain.TriviaModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * todo Правильнее будет разбить на 2 интерактора в каждом свой метод или UseCase-ы сделать
 *
 * @author Юрий Польщиков on 05.09.2021
 */
class TriviaInteractorImpl @Inject constructor(
    private val triviaRepository: TriviaRepository
) : TriviaInteractor {

    /**
     * todo Сделать здесь модель типа успех или неуспех,
     * экрану StartGame не нужны конкретные данные,
     * Если не успех, то попытаться загрузить игру из локального хранилища (сохранять туда загруженные игры)
     * если в локальном хранилище ничего нет, то показать ошибку и вернуться на главный экран
     */
    override fun requestQuestions(): Flow<TriviaResponseModel> {
        // todo request logic here
        return triviaRepository.requestQuestions(5)
    }

    /**
     *
     */
    override fun getQuestions(): Flow<TriviaModel> {
        return triviaRepository.getAllQuestions()
    }
}
