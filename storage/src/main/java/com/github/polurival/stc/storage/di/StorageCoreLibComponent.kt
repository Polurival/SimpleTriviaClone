package com.github.polurival.stc.storage.di

import com.github.polurival.stc.coreapi.context.ContextCoreLibApi
import com.github.polurival.stc.storageapi.StorageCoreLibApi
import dagger.Component

/**
 *
 *
 * @author Юрий Польщиков on 12.09.2021
 */
@Component(
    modules = [StorageCoreLibModule::class],
    dependencies = [ContextCoreLibApi::class]
)
interface StorageCoreLibComponent : StorageCoreLibApi {

    @Component.Factory
    interface Factory {
        fun create(
            contextCoreLibApi: ContextCoreLibApi
        ): StorageCoreLibComponent
    }
}
