package com.example.brainstorm.Presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.brainstorm.Domain.entity.Level

// тут проблема что я пытаюсь наследоваться от интерфейса,
// который имеет методы с аннотацией @JvmDefault.
// Эта аннотация используется для методов интерфейса в Kotlin,
// чтобы предоставить реализацию по умолчанию.
//Однако компилятор Kotlin требует использования опции -Xjvm-default
// для разрешения наследования от интерфейсов с такими методами.
//я попробовал добавить её в gradle файл но не помогло поэтому потом как-нибудь попробую исправить.
class GameViewModelFactory(
     val level: Level,
     val application: Application
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(level,application) as T
        }
        throw RuntimeException("Unknown view model class $modelClass")
    }
}