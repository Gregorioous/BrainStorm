package com.example.brainstorm.Domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings(
    val maxSumValue:Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers : Int,
    val gameTimeImSeconds :Int
                ): Parcelable

