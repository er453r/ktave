package com.er453r.ktave

import com.er453r.ktave.lang.ARithmetic
import com.er453r.ktave.lang.Number
import com.er453r.ktave.lang.Space
import mu.KotlinLogging

class Parser(private val string: String) {
    private val log = KotlinLogging.logger {}

    init {
        log.info { "Parsing $string" }

        val tokens = arrayOf(
            Number(),
            Space(),
            ARithmetic()
        )

        var position = 0

        next@ while(position < string.length){
            for (token in tokens){
                val match = token.regex.find(string, position)?.takeIf { it.range.start == position }

                if(match != null){
                    log.info { "Found ${match.value}" }

                    position += match.value.length

                    continue@next
                }
            }

            log.error { "No matching tokens found! For \"${string.substring(position)}\"" }

            break
        }

        log.info { "Parsing successful!" }
    }
}
