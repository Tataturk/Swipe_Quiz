package com.example.swipequiz

data class Questions(
    var question: String,
    var statement: Boolean
)  {
    companion object    {
        val QUESTIONS_STRING = arrayOf(
            "A val and var are the same",
            "Mobile Application Development grants 12 ECTS.",
            "A Unit in Kotlin corresponds to a void in Java",
            "In Kotlin 'when' replaces the 'switch' operator in Java"
        )

        val QUESTIONS_ANSWERS = booleanArrayOf(
            false,
            true,
            true,
            false
        )
    }
}