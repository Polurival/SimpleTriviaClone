package com.github.polurival.stc.game.data

import android.util.Log
import com.github.polurival.stc.gameapi.data.TriviaResponseModel
import com.github.polurival.stc.gameapi.domain.Quiz
import com.github.polurival.stc.gameapi.domain.TriviaModel
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

/**
 *
 *
 * @author Юрий Польщиков on 02.10.2021
 */
class TriviaResponseConverterImpl @Inject constructor() : TriviaResponseConverter {

    override fun convertTriviaModelToJson(model: TriviaResponseModel): String {
        val all = JSONObject()

        val results = JSONArray()
        model.results.forEach {
            val question = JSONObject()
            question.put("category", it.category)
            question.put("type", it.type)
            question.put("difficulty", it.difficulty)
            question.put("question", it.question)
            question.put("correct_answer", it.correctAnswer)

            val incorrectAnswers = JSONArray()
            it.incorrectAnswers.forEach { incorrectAnswer ->
                incorrectAnswers.put(incorrectAnswer)
            }
            question.put("incorrect_answers", incorrectAnswers)

            results.put(question)
        }
        all.put("results", results)

        return all.toString()
    }

    override fun convertJsonToTriviaModel(json: String): TriviaModel {
        try {
            val quizes = ArrayList<Quiz>()

            val all = JSONObject(json)
            val results = all.getJSONArray("results")
            for (i in 0 until results.length()) {
                val rawQuiz = results.getJSONObject(i)

                val incorrectAnswers = ArrayList<String>()
                val rawIncorrectAnswers = rawQuiz.getJSONArray("incorrect_answers")
                for (j in 0 until rawIncorrectAnswers.length()) {
                    incorrectAnswers.add(rawIncorrectAnswers.getString(j))
                }
                val correctAnswer = rawQuiz.getString("correct_answer")

                val allAnswers = incorrectAnswers + correctAnswer

                quizes.add(
                    Quiz(
                        question = rawQuiz.getString("question"),
                        correctAnswer = correctAnswer,
                        allAnswers = allAnswers.shuffled()
                    )
                )
            }
            return TriviaModel(quizes)
        } catch (e: JSONException) {
            Log.e("TriviaResponseConverter", e.toString())
            return TriviaModel(emptyList())
        }
    }
}
