package com.github.polurival.stc.game.data

import com.github.polurival.stc.gameapi.data.TriviaResponseModel
import com.github.polurival.stc.gameapi.domain.TriviaModel

/**
 *
 *
 * @author Юрий Польщиков on 02.10.2021
 */
interface TriviaResponseConverter {

    fun convertTriviaModelToJson(model: TriviaResponseModel): String

    fun convertJsonToTriviaModel(json: String): TriviaModel
}
