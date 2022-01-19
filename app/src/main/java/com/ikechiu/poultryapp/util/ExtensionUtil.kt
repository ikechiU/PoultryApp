package com.ikechiu.poultryapp.util

import java.text.SimpleDateFormat
import java.util.*


private val dateCatalog = SimpleDateFormat("EEEE, MMMM dd, yyyy HH:mm:ss")

fun dateCatalog(date: Date): String {
    return dateCatalog.format(date)
}

fun isEqual(stringOne: String, stringTwo: String): Boolean {
    return (stringOne == stringTwo)
}

fun isNumber(s: String?): Boolean {
    return if (s.isNullOrEmpty()) false else s.all { Character.isDigit(it) }
}

fun avoidEmptyString(s: String): String {
    return if ((isNumber(s))) s else "0"
}

fun returnInt(s: String): Int {
    return avoidEmptyString(s).toInt()
}

fun preventNonNumerics(s: String): String {
    return if (s.isEmpty()) {
        ""
    } else {
        s.substring(0, s.length - 1)
    }
}