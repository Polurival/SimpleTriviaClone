package com.github.polurival.stc.core.di.coroutines

import com.github.polurival.stc.coreapi.coroutines.DispatchersCoreLibApi
import dagger.Component

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
@Component(modules = [DispatchersCoreLibModule::class])
interface DispatchersCoreLibComponent : DispatchersCoreLibApi
