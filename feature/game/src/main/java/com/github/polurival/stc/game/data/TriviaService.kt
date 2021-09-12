package com.github.polurival.stc.game.data

import com.github.polurival.stc.gameapi.data.TriviaResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * example url: https://opentdb.com/api.php?amount=10&category=12&difficulty=medium&type=multiple&encode=base64
 *
 * @author Юрий Польщиков on 09.08.2021
 */
interface TriviaService {

    @GET("api.php?")
    suspend fun getQuestions(@Query("amount") amount: Int): TriviaResponseModel
}
