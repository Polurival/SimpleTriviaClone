package com.github.polurival.stc.coreapi.network

import com.github.polurival.stc.coreapi.di.Api
import retrofit2.Retrofit

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
interface NetworkCoreLibApi : Api {

    fun retrofit(): Retrofit
}
