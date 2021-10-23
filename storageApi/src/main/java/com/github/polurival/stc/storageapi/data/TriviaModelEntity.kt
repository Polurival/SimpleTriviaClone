package com.github.polurival.stc.storageapi.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Entity в БД для хранения одного вопроса с ответами
 *
 * @author Юрий Польщиков on 23.10.2021
 */
@Entity(tableName = "trivia_questions", indices = [Index("question")])
data class TriviaModelEntity(
    @PrimaryKey(autoGenerate = true)
    val localId: Long = 0,
    val question: String,
    val category: String,
    val type: String,
    val difficulty: String,
    @ColumnInfo(name = "correct_answer") val correctAnswer: String,
    @ColumnInfo(name = "incorrect_answers") val incorrectAnswers: List<String>
)
