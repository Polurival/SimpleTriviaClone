package com.github.polurival.stc.app.di

import android.app.Application
import com.github.polurival.stc.core.di.context.DaggerContextCoreLibComponent
import com.github.polurival.stc.core.di.coroutines.DaggerDispatchersCoreLibComponent
import com.github.polurival.stc.core.di.network.DaggerNetworkCoreLibComponent
import com.github.polurival.stc.coreapi.context.ContextCoreLibApi
import com.github.polurival.stc.coreapi.coroutines.DispatchersCoreLibApi
import com.github.polurival.stc.coreapi.di.Api
import com.github.polurival.stc.coreapi.network.NetworkCoreLibApi
import com.github.polurival.stc.storage.di.DaggerStorageCoreLibComponent
import com.github.polurival.stc.storageapi.StorageCoreLibApi
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
class CoreApiModule(private val application: Application) {

    private val contextApi: ContextCoreLibApi by lazy(LazyThreadSafetyMode.NONE) {
        DaggerContextCoreLibComponent.factory().create(application, application.applicationContext)
    }

    @Provides
    @IntoMap
    @ClassKey(ContextCoreLibApi::class)
    @CoreApis
    fun contextApi(): Api = contextApi

    @Provides
    @IntoMap
    @ClassKey(DispatchersCoreLibApi::class)
    @CoreApis
    fun dispatchersApi(): Api = DaggerDispatchersCoreLibComponent.create()

    @Provides
    @IntoMap
    @ClassKey(NetworkCoreLibApi::class)
    @CoreApis
    fun networkApi(): Api = DaggerNetworkCoreLibComponent.create()

    @Provides
    @IntoMap
    @ClassKey(StorageCoreLibApi::class)
    @CoreApis
    fun storageApi(): Api = DaggerStorageCoreLibComponent.factory().create(contextApi)
}
