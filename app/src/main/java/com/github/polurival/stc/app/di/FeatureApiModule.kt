package com.github.polurival.stc.app.di

import com.github.polurival.stc.coreapi.di.Api
import com.github.polurival.stc.coreapi.di.get
import com.github.polurival.stc.game.di.DaggerGameComponent
import com.github.polurival.stc.gameapi.GameFeatureApi
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
@Module
object FeatureApiModule {

    @Provides
    @IntoMap
    @ClassKey(GameFeatureApi::class)
    @FeatureApis
    fun gameFeatureApi(
        @CoreApis coreApis: Map<Class<*>, @JvmSuppressWildcards Api>
    ): Api =
        DaggerGameComponent.factory()
            .create(
                dispatchersCoreLibApi = coreApis.get(),
                networkCoreLibApi = coreApis.get(),
                storageCoreLibApi = coreApis.get(),
            )
}
