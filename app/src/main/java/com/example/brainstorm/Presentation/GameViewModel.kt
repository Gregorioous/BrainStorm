package com.example.brainstorm.Presentation

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.brainstorm.Data.GameRepositoryImpl
import com.example.brainstorm.Domain.entity.GameResult
import com.example.brainstorm.Domain.entity.GameSettings
import com.example.brainstorm.Domain.entity.Level
import com.example.brainstorm.Domain.entity.Question
import com.example.brainstorm.Domain.usecases.GenerateQuestionsUseCase
import com.example.brainstorm.Domain.usecases.GetGameSettingUseCase
import com.example.brainstorm.R

class GameViewModel(application: Application):AndroidViewModel(application) {


    private lateinit var gameSettings: GameSettings
    private lateinit var level: Level
    private val repository = GameRepositoryImpl
    private var timer: CountDownTimer? = null
    private val context = application
    private var countOfRightAnswers = 0
    private var countOfQuestion = 0
    private val generateQuestionsUseCase = GenerateQuestionsUseCase(repository)
    private val getGameSettingUseCase = GetGameSettingUseCase(repository)


    private val _formatetTime = MutableLiveData<String>()
    val formatetTime :LiveData<String>
    get() = _formatetTime

    private val _question = MutableLiveData<Question>()
    val question :LiveData<Question>
        get() = _question

    private val _progressAnswers = MutableLiveData<String>()
    val progressAnswers :LiveData<String>
        get() = _progressAnswers

    private val _enoughCount = MutableLiveData<Boolean>()
    val enoughCount :LiveData<Boolean>
        get() = _enoughCount

    private val _enoughPercent = MutableLiveData<Boolean>()
    val enoughPercent :LiveData<Boolean>
        get() = _enoughPercent

    private val _percentOfRightAnswer = MutableLiveData<Int>()
     val percentOfRightAnswer:LiveData<Int>
     get() = _percentOfRightAnswer

    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult:LiveData<GameResult>
        get() = _gameResult


    private val _minPercent = MutableLiveData<Int>()
    val minPercent:LiveData<Int>
        get() = _minPercent

     fun startGame(){
         GetGameSettings(level)
         startTimer()
         generateQuestion()
    }
    fun chooseAnswer(number:Int){
        checkAnswer(number)
        updateProgress()
        generateQuestion()
    }

    private fun updateProgress(){
        val percent = calculatePercenrOfRightAnswers()
        _percentOfRightAnswer.value = percent
        _progressAnswers.value = String.format(
            context.resources.getString(R.string.progress_answers),
            countOfRightAnswers,
            gameSettings.minCountOfRightAnswers
        )
        _enoughCount.value = countOfRightAnswers >= gameSettings.minCountOfRightAnswers
        _enoughPercent.value = percent >= gameSettings.minPercentOfRightAnswers
    }

    private fun calculatePercenrOfRightAnswers():Int{
            return ((countOfRightAnswers / countOfQuestion.toDouble()) * 100).toInt()
    }

    private fun checkAnswer(number: Int){
        val rightAnswer = question.value?.rightAnswer
        if (number == rightAnswer){
            countOfRightAnswers++
        }
        countOfQuestion++
    }

    private fun startTimer(){
        timer =  object : CountDownTimer(
            gameSettings.gameTimeImSeconds * MILLIS_IN_SECONDS,
            MILLIS_IN_SECONDS
        ){
            override fun onTick(millisUntilFinished: Long) {
                _formatetTime.value = formatedTime(millisUntilFinished)
            }

            override fun onFinish() {
               finishGame()
            }

        }
        timer?.start()
    }

    private fun generateQuestion(){
       _question.value = generateQuestionsUseCase(gameSettings.maxSumValue)
    }

    private fun GetGameSettings(level: Level) {
        this.level = level
        this.gameSettings = getGameSettingUseCase(level)
        _minPercent.value = gameSettings.minPercentOfRightAnswers
    }

    private fun formatedTime(millisUntilFinished: Long):String{
        val seconds = millisUntilFinished / MILLIS_IN_SECONDS
        val minutes = seconds / SECONDS_IN_MINUTES
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTES)
    return String.format("%02d:%02d",minutes,leftSeconds)
    }

    private fun finishGame(){
        _gameResult.value = GameResult(
            enoughCount.value == true && enoughPercent.value == true,
            countOfRightAnswers,
            countOfQuestion,
            gameSettings
        )
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    companion object{
        private const val  MILLIS_IN_SECONDS = 1000L
        private const val  SECONDS_IN_MINUTES = 60
    }
}