package com.er453r.ktave.lang

import com.er453r.ktave.parser.Token

class Arithmetic() : Token {
    override val regex = Regex("\\+")

    private constructor(statement: String): this(){

    }

    override fun fromString(statement: String) = Arithmetic(statement)
}
