package com.er453r.ktave.lang

class Number() : Node() {
    private var value = 0.0

    internal constructor(statement: String) : this() {
        value = statement.toDouble()
    }

    override val regex = Regex("\\d+")
    override fun fromString(statement: String) = Number(statement)
}
