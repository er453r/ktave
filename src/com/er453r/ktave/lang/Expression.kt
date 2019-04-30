package com.er453r.ktave.lang

import com.er453r.ktave.parser.Token

interface Expression : Token {
    val value:Double
}
