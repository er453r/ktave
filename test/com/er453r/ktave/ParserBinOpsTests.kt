package com.er453r.ktave

import com.er453r.ktave.lang.Arithmetic
import com.er453r.ktave.lang.ArithmeticConsumer
import com.er453r.ktave.lang.Number
import com.er453r.ktave.lang.Space
import com.er453r.ktave.parser.ParserException
import mu.KotlinLogging
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertFailsWith

class ParserBinOpsTests {
    companion object {
        const val MAX_DELTA = 1e-6
    }

    private val log = KotlinLogging.logger {}

    private fun eval(statement: String, expected: Double = 0.0) {
        val consumer = ArithmeticConsumer()

        val parser = Parser(
            tokens = arrayOf(
                Number(),
                Space(),
                Arithmetic()
            ),
            tokenConsumer = consumer
        )

        parser.parse(statement)

        val result = consumer.value

        log.info { "Eval \"$statement\" got \"$result\"" }

        assert(abs(expected - result) < MAX_DELTA)
    }

    @Test
    fun `Unary bin op tests`() {
        eval("+2", 2.0)
        eval("-1", -1.0)
        eval("3", 3.0)

        assertFailsWith(ParserException::class) {
            eval("*1")
        }

        assertFailsWith(ParserException::class) {
            eval("/1")
        }
    }

    @Test
    fun `Simple bin op tests`() {
        eval("3 + 2", 5.0)
        eval("3 -1", 2.0)
        eval("3* 2", 6.0)
        eval("6 / 2", 3.0)
    }

    @Test
    fun `Simple float tests`() {
        eval("3.21 + 2.56", 5.77)
        eval("3.21 - 2.56", 0.65)
        eval("3.2 * 2.5", 8.0)
        eval("3.2 / 1.6", 2.0)
    }

    @Test
    fun `Operator precedence tests`() {
        eval("2 + 3 * 4", 14.0)
        eval("2 * 3 + 4", 10.0)

        eval("2 + 3 + 4 + 5", 14.0)
        eval("2 * 3 * 4 * 5", 120.0)
        eval("2 * 3 / 4 + 5", 6.5)
        eval("2 + 3 * 4 + 5", 19.0)
        eval("2 * 3 * 4 / 5", 4.8)
    }
}
