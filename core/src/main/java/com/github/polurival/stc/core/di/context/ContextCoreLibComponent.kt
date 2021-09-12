package com.github.polurival.stc.core.di.context

import android.app.Application
import android.content.Context
import com.github.polurival.stc.coreapi.context.ApplicationContext
import com.github.polurival.stc.coreapi.context.ContextCoreLibApi
import dagger.BindsInstance
import dagger.Component

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
@Component
interface ContextCoreLibComponent : ContextCoreLibApi {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
            @BindsInstance @ApplicationContext context: Context
        ): ContextCoreLibComponent
    }
}
