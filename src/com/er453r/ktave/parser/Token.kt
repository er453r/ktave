package com.er453r.ktave.parser

interface Token {
    val regex: Regex

    fun fromString(statement:String):Token
}
