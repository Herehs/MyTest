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

    private val _userSelected = MutableStateFlow<Map<Int, String>>(emptyMap())
    val userSelected: StateFlow<Map<Int, String>> = _userSelected.asStateFlow()

    private val _correctAnswers = MutableStateFlow<Int>(0)
    val correctAnswers: StateFlow<Int> = _correctAnswers.asStateFlow()

    fun increaseCount(id: Int){
        val answers = _userSelected.value.toMutableMap()
        if(id !in answers) {
            _count.update { it + 1 }
        }
    }

    fun loadQuestions(context: Context){
        val newQuiz = parseQuestions(loadJsonFromRaw(context, R.raw.questions))
        println("Loaded quiz: ${newQuiz.questions.size} questions")
        _quiz.update {
            it.copy(
                questions = newQuiz.questions
            )
        }
    }

    fun LoadUserQuestions(test: String){
        val newQuiz = parseQuestions(test)
        _quiz.update {
            it.copy(
                questions = newQuiz.questions
            )
        }
    }

    fun onAnswerSelected(questionId: Int, answer: String){
        println("onAnswerSelected called — questionId=$questionId, answer=$answer")
        val currAnswers = _userSelected.value.toMap()
        val answers = mapOf(questionId to answer)
        _userSelected.update { currAnswers + answers }
        println("Selected: ${_userSelected.value}")
    }

    fun checkAnswers(){
        val answer = _userSelected.value
        _quiz.value.questions.forEachIndexed { index, question ->
            println(question.answers)
            println(answer)

            if(question.answers[question.correctAnswer] == answer[index]){
                println("proshlo")
                _correctAnswers.update { it + 1 }
            }
        }
        println(_correctAnswers.value)
    }
}