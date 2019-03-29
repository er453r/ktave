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

        while(position < string.length){
            var found = false

            for (token in tokens){
                token.regex.find(string, position)?.takeIf { it.range.start == position }?.let {
                    log.info { "Found ${it.value}" }

                    position += it.value.length

                    found = true
                }

                if(found)
                    break
            }

            if(found)
                continue

            log.error { "No matching tokens found! For \"${string.substring(position)}\"" }

            break
        }

        log.info { "Parsing successful!" }
    }
}
