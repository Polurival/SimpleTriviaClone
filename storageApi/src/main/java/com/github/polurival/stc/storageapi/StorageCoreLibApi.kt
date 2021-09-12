package com.github.polurival.stc.storageapi

import com.github.polurival.stc.coreapi.di.Api

/**
 *
 *
 * @author Юрий Польщиков on 12.09.2021
 */
interface StorageCoreLibApi : Api {

    val preferencesManager: PreferencesManager
}
