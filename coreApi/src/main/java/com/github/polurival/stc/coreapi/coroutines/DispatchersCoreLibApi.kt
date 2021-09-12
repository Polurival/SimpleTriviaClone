package com.github.polurival.stc.coreapi.coroutines

import com.github.polurival.stc.coreapi.di.Api

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
interface DispatchersCoreLibApi : Api {

    val coroutineDispatchers: CoroutineDispatchers
}
