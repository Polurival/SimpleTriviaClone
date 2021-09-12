package com.github.polurival.stc.gameapi.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 *
 * @author Юрий Польщиков on 09.08.2021
 */
@Parcelize
@Serializable
data class TriviaResponseModel(
    @SerialName("response_code") val responseCode: Int,
    val results: List<Results>
) : Parcelable

@Parcelize
@Serializable
data class Results(
    val category: String,
    val type: String,
    val difficulty: String,
    val question: String,
    @SerialName("correct_answer") val correctAnswer: String,
    @SerialName("incorrect_answers") val incorrectAnswers: List<String>
) : Parcelable
