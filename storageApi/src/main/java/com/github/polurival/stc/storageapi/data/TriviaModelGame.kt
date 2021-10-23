package com.github.polurival.stc.storageapi.data

/**
 * Модель-обертка, понадобившаяся чтобы Room смог сгенерировать Dao класс
 *
 * @author Юрий Польщиков on 23.10.2021
 */
class TriviaModelGame(val entities: List<TriviaModelEntity>)
