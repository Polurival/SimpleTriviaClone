package com.github.polurival.stc.coreapi.di

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
interface ApiContainer {

    fun <T> getCore(key: Class<T>): T

    fun <T> getFeature(key: Class<T>): T
}

inline fun <reified T> ApiContainer.getCore(): T =
    getCore(T::class.java)

inline fun <reified T> ApiContainer.getFeature(): T =
    getFeature(T::class.java)
