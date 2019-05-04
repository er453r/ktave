package com.er453r.ktave

import com.er453r.ktave.lang.*
import com.er453r.ktave.lang.Number
import mu.KotlinLogging
import kotlin.math.abs
import kotlin.test.Test

class ParenthesisTests {
    private val log = KotlinLogging.logger {}

    private fun eval(statement: String, expected: Double = 0.0) {
        val consumer = ArithmeticConsumer()

        val parser = Parser(
            tokens = arrayOf(
                Number(),
                Space(),
                Arithmetic(),
                Parenthesis()
            ),
            tokenConsumer = consumer
        )

        parser.parse(statement)

        val result = consumer.value

        log.info { "Eval \"$statement\" got \"$result\"" }

        assert(abs(expected - result) < ParserBinOpsTests.MAX_DELTA)
    }

    @Test
    fun `Parenthesis tests`() {
        eval("(2 + 3) * 4", 20.0)
        eval("2 * (3 + 4)", 14.0)
    }
}
