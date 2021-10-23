package com.github.polurival.stc.storage.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.github.polurival.stc.storageapi.data.TriviaDao
import com.github.polurival.stc.storageapi.data.TriviaModelEntity
import com.github.polurival.stc.storageapi.data.TriviaModelGame

/**
 * Интерфейс для взаимодействия с БД вопросов и ответов
 *
 * @author Юрий Польщиков on 23.10.2021
 */
@Dao
abstract class TriviaDaoImpl : TriviaDao {

    @Transaction
    override suspend fun insertNewTriviaModels(vararg triviaModelEntity: TriviaModelEntity) {
        val newModels = mutableListOf<TriviaModelEntity>()

        for (entity in triviaModelEntity) {
            val row = getTriviaModelByQuestion(entity.question)
            if (row == null) {
                newModels.add(entity)
            }
        }
        insertTriviaModels(*newModels.toTypedArray())
    }

    @Transaction
    override suspend fun getTriviaModelsForOfflineGame(): TriviaModelGame {
        val indexSet = HashSet<Long>()
        val availableLocalQuestions = getLocalQuestionsCount()
        while (indexSet.size < QUIZ_QUESTION_COUNT) {
            val randomIndex = (Math.random() * availableLocalQuestions).toLong()
            indexSet.add(randomIndex)
        }
        return TriviaModelGame(getTriviaModelsByIds(indexSet.toList()))
    }

    /**
     * Узнать по вопросу, сохранен ли он уже в локальной БД
     */
    @Query("SELECT * FROM trivia_questions WHERE question = (:question)")
    abstract suspend fun getTriviaModelByQuestion(question: String): TriviaModelEntity?

    /**
     * Добавить произвольное количество моделей - вопрос+ответы
     */
    @Insert
    abstract suspend fun insertTriviaModels(vararg triviaModelEntity: TriviaModelEntity)

    /**
     * Узнать количество сохраненных локально вопросов, для определения рандомных id для выборки
     */
    @Query("SELECT COUNT(*) FROM trivia_questions")
    abstract suspend fun getLocalQuestionsCount(): Long

    /**
     * todo Какой-то глюк если сделать этот метод suspend - не генерится
     */
    @Query("SELECT * FROM trivia_questions WHERE localId IN (:localIds)")
    abstract fun getTriviaModelsByIds(localIds: List<Long>): List<TriviaModelEntity>

    companion object {
        private const val QUIZ_QUESTION_COUNT = 5
    }
}
