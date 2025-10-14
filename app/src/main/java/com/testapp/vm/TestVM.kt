package com.testapp.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import com.testapp.R
import com.testapp.util.Question
import com.testapp.util.Quiz
import com.testapp.util.loadJsonFromRaw
import com.testapp.util.parseQuestions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TestViewModel(): ViewModel(){

    private val _count = MutableStateFlow<Int>(0)
    val count: StateFlow<Int> = _count.asStateFlow()

    private val _quiz = MutableStateFlow<Quiz>(Quiz(questions = listOf(Question(title = "a", answers = mutableListOf("a", "b"), correctAnswer = 1))))
    val quiz: StateFlow<Quiz> = _quiz.asStateFlow()

    private val _userSelected = MutableStateFlow<Map<String, Int>>(emptyMap())
    val userSelected: StateFlow<Map<String, Int>> = _userSelected.asStateFlow()

    fun increaseCount(){
        _count.update { it + 1 }
    }

    fun loadQuestions(context: Context){
        _quiz.update { parseQuestions(loadJsonFromRaw(context = context, resourceId = R.raw.questions)) }
    }
}