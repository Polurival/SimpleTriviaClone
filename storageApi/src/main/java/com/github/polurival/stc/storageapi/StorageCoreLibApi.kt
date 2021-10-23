package com.github.polurival.stc.storageapi

import com.github.polurival.stc.coreapi.di.Api
import com.github.polurival.stc.storageapi.data.TriviaDao

/**
 *
 *
 * @author Юрий Польщиков on 12.09.2021
 */
interface StorageCoreLibApi : Api {

    /**
     * Менеджер для работы с общим файлом настроек
     */
    val preferencesManager: PreferencesManager

    /**
     * Интерфейс для взаимодействия с БД вопросов и ответов
     */
    val triviaDao: TriviaDao
}
