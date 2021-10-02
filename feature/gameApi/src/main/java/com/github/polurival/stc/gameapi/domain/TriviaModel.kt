package com.github.polurival.stc.gameapi.domain

/**
 *
 *
 * @author Юрий Польщиков on 02.10.2021
 */
class TriviaModel(
    val quizes: List<Quiz>
)

class Quiz(
    val question: String,
    val correctAnswer: String,
    val allAnswers: List<String>
)
