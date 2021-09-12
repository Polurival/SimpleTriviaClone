package com.github.polurival.stc.core.di.network

import com.github.polurival.stc.coreapi.network.NetworkCoreLibApi
import dagger.Component

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
@Component(modules = [NetworkCoreLibModule::class])
interface NetworkCoreLibComponent : NetworkCoreLibApi
