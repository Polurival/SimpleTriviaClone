package com.github.polurival.stc.game.domain

import com.github.polurival.stc.game.data.TriviaRepository
import com.github.polurival.stc.gameapi.data.TriviaResponseModel
import com.github.polurival.stc.gameapi.domain.TriviaInteractor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
class TriviaInteractorImpl @Inject constructor(
    private val triviaRepository: TriviaRepository
) : TriviaInteractor {

    override fun getQuestions(): Flow<TriviaResponseModel> {
        // todo request logic here
        return triviaRepository.getQuestions(5)
    }
}
