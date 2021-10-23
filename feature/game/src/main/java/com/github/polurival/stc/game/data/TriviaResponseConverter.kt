package com.github.polurival.stc.game.data

import com.github.polurival.stc.gameapi.data.TriviaResponseModel
import com.github.polurival.stc.gameapi.domain.TriviaModel
import com.github.polurival.stc.storageapi.data.TriviaModelEntity

/**
 *
 *
 * @author Юрий Польщиков on 02.10.2021
 */
interface TriviaResponseConverter {

    fun convertTriviaModelToJson(model: TriviaResponseModel): String

    fun convertJsonToTriviaModel(json: String): TriviaModel

    fun convertTriviaModelToEntity(model: TriviaResponseModel): Array<TriviaModelEntity>

    fun convertEntitiesToJson(models: List<TriviaModelEntity>): String
}
