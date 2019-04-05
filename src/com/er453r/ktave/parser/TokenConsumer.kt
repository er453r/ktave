package com.er453r.ktave.parser

interface TokenConsumer<T : Token<T>> {
    fun addToken(token: T)
}
