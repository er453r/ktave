package com.er453r.ktave.parser

class ParserException(message: String, val column: Int, val line: Int) : Exception(message)
