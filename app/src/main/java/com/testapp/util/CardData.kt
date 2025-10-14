package com.testapp.util

import kotlinx.serialization.Serializable

@Serializable
data class Question(val title: String, val answers: List<String>, val correctAnswer: Int)

@Serializable
data class Quiz(val questions: List<Question>)