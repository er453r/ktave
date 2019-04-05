package com.er453r.ktave.parser

interface Token<T : Token<T>> {
    val regex: Regex

    fun fromString(statement: String): T
}
