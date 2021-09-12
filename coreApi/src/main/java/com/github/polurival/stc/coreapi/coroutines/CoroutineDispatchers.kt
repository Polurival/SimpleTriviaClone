package com.github.polurival.stc.coreapi.coroutines

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Диспетчеры для корутин
 *
 * @author Юрий Польщиков on 05.09.2021
 */
interface CoroutineDispatchers {
    /**
     * [CoroutineDispatcher] используемый для UI-задач.
     */
    val ui: CoroutineDispatcher

    /**
     * [CoroutineDispatcher] используемый для по возможности немедленого выполнения UI-задач.
     */
    val uiImmediate: CoroutineDispatcher

    /**
     * [CoroutineDispatcher] используемый для фоновых IO-задач.
     */
    val io: CoroutineDispatcher

    /**
     * стандартный диспатчер
     */
    val default: CoroutineDispatcher
}
