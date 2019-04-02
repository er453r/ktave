package com.er453r.ktave

import com.er453r.ktave.parser.ParserException
import com.er453r.ktave.parser.Token
import com.er453r.ktave.parser.TokenConsumer
import mu.KotlinLogging

class Parser(private val tokens: Array<Token>, private val tokenConsumer: TokenConsumer) {
    companion object {
        private const val NEW_LINE = "\n"
    }

    private val log = KotlinLogging.logger {}

    fun parse(statement: String) {
        log.info { "Parsing $statement" }

        var position = 0
        var column = 1
        var line = 1

        next@ while (position < statement.length) {
            for (token in tokens) {
                val match = token.regex.find(statement, position)?.takeIf { it.range.start == position }

                if (match != null) {
                    position += match.value.length

                    val newLinePosition = match.value.indexOf(NEW_LINE)

                    if (newLinePosition > -1) {
                        line++
                        column = match.value.length - newLinePosition
                    } else
                        column += match.value.length

                    continue@next
                }
            }

            throw ParserException("Unknown character: \"${statement.substring(position)}\"", column, line)
        }

        log.info { "Parsing successful!" }
    }
}
