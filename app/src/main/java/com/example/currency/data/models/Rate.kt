package com.example.currency.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates")
data class Rate(

    @PrimaryKey(autoGenerate = false)
    val pair: String,

    val rate: Double
)
