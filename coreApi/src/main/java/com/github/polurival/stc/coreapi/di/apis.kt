package com.github.polurival.stc.coreapi.di

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
inline fun <reified T : Api> Map<@JvmSuppressWildcards Class<*>, Api>.get(): T =
    this[T::class.java] as T
