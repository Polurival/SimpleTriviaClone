package com.github.polurival.stc.storageapi.data

import androidx.annotation.WorkerThread

/**
 * Внешний интерфейс для взаимодействия с БД вопросов и ответов
 *
 * @author Юрий Польщиков on 23.10.2021
 */
interface TriviaDao {

    /**
     * Добавить в БД новые, ранее не добавленные модели - вопрос+ответы
     */
    @WorkerThread
    suspend fun insertNewTriviaModels(vararg triviaModelEntity: TriviaModelEntity)

    /**
     * Сделать рандомную выборку вопросов-ответов для игры
     */
    @WorkerThread
    suspend fun getTriviaModelsForOfflineGame(): TriviaModelGame
}
