package com.github.polurival.stc.coreapi.context

import android.app.Application
import android.content.Context
import com.github.polurival.stc.coreapi.di.Api

/**
 *
 *
 * @author Юрий Польщиков on 05.09.2021
 */
interface ContextCoreLibApi : Api {

    val application: Application

    @ApplicationContext
    val context: Context
}
