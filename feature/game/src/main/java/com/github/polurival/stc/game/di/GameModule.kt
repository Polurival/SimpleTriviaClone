package com.github.polurival.stc.game.di

import com.github.polurival.stc.game.data.TriviaRepository
import com.github.polurival.stc.game.data.TriviaRepositoryImpl
import com.github.polurival.stc.game.domain.TriviaInteractorImpl
import com.github.polurival.stc.gameapi.domain.TriviaInteractor
import dagger.Binds
import dagger.Module
import dagger.Reusable

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
@Module(includes = [GameNetworkModule::class])
interface GameModule {

    @Binds
    @Reusable
    fun bindsTriviaRepository(impl: TriviaRepositoryImpl): TriviaRepository

    @Binds
    @Reusable
    fun bindsTriviaInteractor(impl: TriviaInteractorImpl): TriviaInteractor
}
