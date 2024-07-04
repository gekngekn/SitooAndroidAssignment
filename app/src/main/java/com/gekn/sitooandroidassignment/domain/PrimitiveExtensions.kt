package com.gekn.sitooandroidassignment.domain

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object PrimitiveExtensions {

    /**
     * Capitalize the first char of the String and add spaces before each upper case char
     */
    fun String.capitalizeAndBlankStepWords() =
        this.replace("(?<=[a-z])(?=[A-Z])".toRegex(), " ").replaceFirstChar { it.uppercase() }

    fun Long.secondsToDateString(): String {
        val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        return formatter.format(Date(this * 1000))
    }

}