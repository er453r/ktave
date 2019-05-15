package com.er453r.ktave.parser

class ParserException(message: String, val column: Int, val line: Int, cause: Exception? = null) : Exception(message, cause)
