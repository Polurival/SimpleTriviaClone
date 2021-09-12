package com.github.polurival.stc.app.di

import com.github.polurival.stc.app.TriviaApp
import com.github.polurival.stc.coreapi.di.Api
import dagger.Component

/**
 *
 *
 * @author Юрий Польщиков on 17.08.2021
 */
@Component(
    modules = [
        CoreApiModule::class,
        FeatureApiModule::class
    ]
)
interface AppComponent {

    @CoreApis
    fun coreApis(): Map<Class<*>, @JvmSuppressWildcards Api>

    @FeatureApis
    fun featuresMap(): Map<Class<*>, @JvmSuppressWildcards Api>

    fun inject(target: TriviaApp)

    @Component.Factory
    interface Factory {
        fun create(coreApiModule: CoreApiModule): AppComponent
    }
}
