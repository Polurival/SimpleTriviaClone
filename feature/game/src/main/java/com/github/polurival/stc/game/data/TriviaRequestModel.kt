package com.github.polurival.stc.game.data

/**
 * @property amount Number of Questions
 * @property category Category of Questions
 * @property difficulty Difficulty of Questions
 * @property type Type of Questions (multiple or true/false)
 * @property encode Encoding
 *
 * @author Юрий Польщиков on 09.08.2021
 */
data class TriviaRequestModel(
    val amount: Int = 5,
    val category: Category = Category.Any,
    val difficulty: Difficulty = Difficulty.Any,
    val type: Type = Type.Any,
    val encode: Encode = Encode.Default
)

/**
 * todo Make request and model from https://opentdb.com/api_category.php
 */
enum class Category(val number: Int?) {
    GeneralKnowledge(9),
    EntertainmentBooks(10),
    //todo from 11 to 31
    EntertainmentCartoonAndAnimations(32),
    Any(null)
}

enum class Difficulty {
    Easy,
    Medium,
    Hard,
    Any
}

/**
 * Type of Questions (multiple, true/false, any)
 */
enum class Type {
    Multiple,
    Boolean,
    Any
}

enum class Encode {
    Url3986,
    Base64,
    Default
}
