package com.er453r.ktave

import com.er453r.ktave.lang.Arithmetic
import com.er453r.ktave.lang.ArithmeticConsumer
import com.er453r.ktave.lang.Number
import com.er453r.ktave.lang.Space
import com.er453r.ktave.parser.ParserException
import com.er453r.ktave.parser.TokenConsumer
import mu.KotlinLogging
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ParserTests {
    private val log = KotlinLogging.logger {}

    private fun testParser(consumer: TokenConsumer = ArithmeticConsumer()) = Parser(
        tokens = arrayOf(
            Number(),
            Space(),
            Arithmetic()
        ),
        tokenConsumer = consumer
    )

    @Test
    fun `General error handling`() {
        assertFailsWith(ParserException::class) {
            testParser().parse("2 +Ź2")
        }
    }

    @Test
    fun `Specific error handling`() {
        try {
            testParser().parse("2 +Ź2")

            assert(false)
        } catch (parserException: ParserException) {
            with(parserException) {
                assertEquals(1, line)
                assertEquals(4, column)
            }
        }
    }

    @Test
    fun `Simple parsing test`() {
        testParser().parse("2 + 2")
    }

    @Test
    fun `Simple addition test`() {
        val arithmeticConsumer = ArithmeticConsumer()

        testParser(arithmeticConsumer).parse("3 + 2")

        val value = arithmeticConsumer.value

        log.info { "Got value $value" }

        assertEquals(value, 5.0)
    }

    @Test
    fun `Simple subtraction test`() {
        val arithmeticConsumer = ArithmeticConsumer()

        testParser(arithmeticConsumer).parse("3 - 2")

        val value = arithmeticConsumer.value

        log.info { "Got value $value" }

        assertEquals(value, 1.0)
    }

    @Test
    fun `Simple multiplication test`() {
        val arithmeticConsumer = ArithmeticConsumer()

        testParser(arithmeticConsumer).parse("3 * 2")

        val value = arithmeticConsumer.value

        log.info { "Got value $value" }

        assertEquals(value, 6.0)
    }

    @Test
    fun `Simple division test`() {
        val arithmeticConsumer = ArithmeticConsumer()

        testParser(arithmeticConsumer).parse("6 / 2")

        val value = arithmeticConsumer.value

        log.info { "Got value $value" }

        assertEquals(value, 3.0)
    }
}
