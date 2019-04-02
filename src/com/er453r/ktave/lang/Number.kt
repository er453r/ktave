package com.er453r.ktave.lang

import com.er453r.ktave.parser.Token

class Number() : Token {
    var value = 0.0

    private constructor(statement: String) : this() {
        value = statement.toDouble()
    }

    override val regex = Regex("\\d+")
    override fun fromString(statement: String) = Number(statement)
}
