package com.example.brainstorm.Domain.repository

import com.example.brainstorm.Domain.entity.GameSettings
import com.example.brainstorm.Domain.entity.Level
import com.example.brainstorm.Domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue:Int,
        countOfOptions :Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}