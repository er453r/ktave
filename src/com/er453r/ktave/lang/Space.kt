package com.er453r.ktave.lang

import com.er453r.ktave.parser.Token

class Space : Token {
    override val regex = Regex(" ")
}
