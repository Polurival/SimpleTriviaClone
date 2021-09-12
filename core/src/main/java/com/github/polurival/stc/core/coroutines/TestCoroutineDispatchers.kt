package com.github.polurival.stc.core.coroutines

import com.github.polurival.stc.coreapi.coroutines.CoroutineDispatchers
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Класс для замены диспетчеров [CoroutineDispatchers] на библиотечный тестовый [CoroutineDispatcher]
 * todo move to testing module
 *
 * @author Юрий Польщиков on 05.09.2021
 */
class TestCoroutineDispatchers(dispatcher: CoroutineDispatcher) : CoroutineDispatchers {

    override val ui: CoroutineDispatcher = dispatcher

    override val uiImmediate: CoroutineDispatcher = dispatcher

    override val io: CoroutineDispatcher = dispatcher

    override val default: CoroutineDispatcher = dispatcher
}
