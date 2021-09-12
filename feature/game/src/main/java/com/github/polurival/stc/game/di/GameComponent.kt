package com.github.polurival.stc.game.di

import com.github.polurival.stc.coreapi.coroutines.DispatchersCoreLibApi
import com.github.polurival.stc.coreapi.network.NetworkCoreLibApi
import com.github.polurival.stc.gameapi.GameFeatureApi
import com.github.polurival.stc.storageapi.StorageCoreLibApi
import dagger.Component

/**
 *
 *
 * @author Юрий Польщиков on 18.08.2021
 */
@Component(
    modules = [
        GameModule::class
    ],
    dependencies = [
        DispatchersCoreLibApi::class,
        NetworkCoreLibApi::class,
        StorageCoreLibApi::class
    ]
)
interface GameComponent : GameFeatureApi {

    @Component.Factory
    interface Factory {
        fun create(
            dispatchersCoreLibApi: DispatchersCoreLibApi,
            networkCoreLibApi: NetworkCoreLibApi,
            storageCoreLibApi: StorageCoreLibApi,
        ): GameComponent
    }
}
