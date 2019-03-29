package com.er453r.ktave

import mu.KotlinLogging

class Parser(private val string: String) {
    private val log = KotlinLogging.logger {}

    init {
        log.info { "Parsing $string" }
    }
}
