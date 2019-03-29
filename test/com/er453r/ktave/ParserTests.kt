package com.er453r.ktave

import mu.KotlinLogging
import org.junit.Test

class ParserTests {
    private val log = KotlinLogging.logger {}

    @Test
    fun parse() {
        log.info { "Parser test start!" }

        Parser("2 + 2")
    }
}
