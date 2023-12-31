package com.example.brainstorm.Data

import com.example.brainstorm.Domain.entity.GameSettings
import com.example.brainstorm.Domain.entity.Level
import com.example.brainstorm.Domain.entity.Question
import com.example.brainstorm.Domain.repository.GameRepository
import java.util.HashSet
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl:GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1
    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
    val sum = Random.nextInt(MIN_SUM_VALUE,maxSumValue + 1)
        val vivsibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - vivsibleNumber
        options.add(rightAnswer)
        val from = max( rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue - 1,rightAnswer + countOfOptions)
        while (options.size < countOfOptions){
            options.add(Random.nextInt(from,to))
        }
        return Question(sum,vivsibleNumber,options.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
    return when (level){
    Level.TEST -> {
        GameSettings(
            10,
            3,
            50,
            8
        )
    }
        Level.EASY -> {
            GameSettings(
                10,
                10,
                70,
                60
            )
        }
        Level.NORMAL -> {
            GameSettings(
                20,
                20,
                80,
                40
            )
        }
        Level.HARD -> {
            GameSettings(
                30,
                30,
                90,
                40
            )
        }
}
    }
}