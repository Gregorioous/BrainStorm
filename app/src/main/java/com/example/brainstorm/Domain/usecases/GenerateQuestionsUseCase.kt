package com.example.brainstorm.Domain.usecases

import com.example.brainstorm.Domain.entity.GameSettings
import com.example.brainstorm.Domain.entity.Question
import com.example.brainstorm.Domain.repository.GameRepository

class GenerateQuestionsUseCase(private val repository: GameRepository) {

    operator fun invoke(masSumValue: Int): Question{
        return repository.generateQuestion(masSumValue, COUNT_OF_OPTIONS)
    }

    private companion object{
        private const val COUNT_OF_OPTIONS = 6
    }

}