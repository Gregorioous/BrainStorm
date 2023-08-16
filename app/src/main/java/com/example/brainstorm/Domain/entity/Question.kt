package com.example.brainstorm.Domain.entity

    data class Question (
        val sum:Int,
        val visibleNumber:Int,
        val opions :List<Int>
            ){
        val rightAnswer:Int
        get() = sum - visibleNumber
    }