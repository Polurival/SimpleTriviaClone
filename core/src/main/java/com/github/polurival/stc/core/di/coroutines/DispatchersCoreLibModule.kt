package com.github.polurival.stc.core.di.coroutines

import com.github.polurival.stc.coreapi.coroutines.CoroutineDispatchers
import com.github.polurival.stc.core.coroutines.CoroutineDispatchersImpl
import dagger.Binds
import dagger.Module

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
@Module
interface DispatchersCoreLibModule {

    @Binds
    fun bindsCoroutineDispatcher(impl: CoroutineDispatchersImpl): CoroutineDispatchers
}
