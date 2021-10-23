package com.github.polurival.stc.storage.db

import androidx.room.TypeConverter
import org.json.JSONArray
import org.json.JSONObject

/**
 *
 *
 * @author Юрий Польщиков on 23.10.2021
 */
class IncorrectAnswersTypeConverter {

    /**
     * todo List<String> слишком общая сущность, лучше сделать специальную модель для неверных ответов
     */
    @TypeConverter
    fun incorrectAnswersFromJson(incorrectAnswers: String): List<String> {
        val result = ArrayList<String>()

        val answers = JSONObject(incorrectAnswers).getJSONArray("incorrect_answers")
        for (i in 0 until answers.length()) {
            result.add(answers.getString(i))
        }

        return result
    }

    @TypeConverter
    fun incorrectAnswersToJson(incorrectAnswers: List<String>): String {
        val result = JSONObject()

        val answers = JSONArray()
        incorrectAnswers.forEach {
            answers.put(it)
        }
        result.put("incorrect_answers", answers)

        return result.toString()
    }
}