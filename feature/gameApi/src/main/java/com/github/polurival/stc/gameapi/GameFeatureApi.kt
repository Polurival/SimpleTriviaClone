package com.github.polurival.stc.gameapi

import com.github.polurival.stc.coreapi.di.Api
import com.github.polurival.stc.gameapi.domain.TriviaInteractor

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
interface GameFeatureApi : Api {

    val triviaInteractor: TriviaInteractor
}
