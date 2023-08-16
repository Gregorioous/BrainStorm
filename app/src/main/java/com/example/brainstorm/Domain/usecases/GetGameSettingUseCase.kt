package com.example.brainstorm.Domain.usecases

import com.example.brainstorm.Domain.entity.GameSettings
import com.example.brainstorm.Domain.entity.Level
import com.example.brainstorm.Domain.repository.GameRepository

class GetGameSettingUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level): GameSettings{
        return repository.getGameSettings(level)
    }
}