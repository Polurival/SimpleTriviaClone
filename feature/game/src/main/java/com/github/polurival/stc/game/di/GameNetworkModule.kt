package com.github.polurival.stc.game.di

import com.github.polurival.stc.game.data.TriviaService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
@Module
internal object GameNetworkModule {

    @Provides
    fun triviaService(retrofit: Retrofit): TriviaService = retrofit.create()
}
