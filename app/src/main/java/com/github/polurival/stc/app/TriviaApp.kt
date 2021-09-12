package com.github.polurival.stc.app

import android.app.Application
import com.github.polurival.stc.app.di.CoreApiModule
import com.github.polurival.stc.app.di.CoreApis
import com.github.polurival.stc.app.di.DaggerAppComponent
import com.github.polurival.stc.app.di.FeatureApis
import com.github.polurival.stc.coreapi.di.Api
import com.github.polurival.stc.coreapi.di.ApiContainer
import javax.inject.Inject

/**
 *
 *
 * @author Юрий Польщиков on 18.08.2021
 */
class TriviaApp : Application(), ApiContainer {

    @Inject
    @CoreApis
    lateinit var coresMap: Map<Class<*>, @JvmSuppressWildcards Api>

    /**
     * Текущее решение плохо тем что компоненты фичей частично создаются сразу
     * todo сделать чтобы создавались при первой необходимости
     */
    @Inject
    @FeatureApis
    lateinit var featuresMap: Map<Class<*>, @JvmSuppressWildcards Api>

    @Suppress("unchecked_cast")
    override fun <T> getCore(key: Class<T>): T = coresMap[key] as T

    @Suppress("unchecked_cast")
    override fun <T> getFeature(key: Class<T>): T = featuresMap[key] as T

    override fun onCreate() {
        DaggerAppComponent.factory()
            .create(CoreApiModule(application = this))
            .inject(this)
        super.onCreate()
    }
}
