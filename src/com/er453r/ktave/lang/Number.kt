package com.er453r.ktave.lang

class Number(override val value: Double = 0.0) : Expression {
    internal constructor(statement: String) : this(statement.toDouble())

    override val regex = Regex("\\d+")
    override fun fromString(statement: String) = Number(statement)
}
