package com.er453r.ktave.lang

import com.er453r.ktave.parser.Token

class Number(override val value: Double = 0.0) : Expression, Token {
    internal constructor(statement: String) : this(statement.toDouble())

    override val regex = Regex("\\d+.?\\d*")
    override fun fromString(statement: String) = Number(statement)
}
