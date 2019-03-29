package com.er453r.ktave.lang

import com.er453r.ktave.parser.Token

class Number : Token {
    override val regex = Regex("\\d+")
}
