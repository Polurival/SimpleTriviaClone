package com.github.polurival.stc.core.coroutines

import com.github.polurival.stc.coreapi.coroutines.CoroutineDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
class CoroutineDispatchersImpl @Inject constructor() : CoroutineDispatchers {

    override val ui: CoroutineDispatcher
        get() = Dispatchers.Main

    override val uiImmediate: CoroutineDispatcher
        get() = Dispatchers.Main.immediate

    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
}
