package com.er453r.ktave

import com.er453r.ktave.lang.Arithmetic
import com.er453r.ktave.lang.ArithmeticConsumer
import com.er453r.ktave.lang.Number
import com.er453r.ktave.lang.Space
import com.er453r.ktave.parser.ParserException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ParserTests {
    private fun testParser() = Parser(
        tokens = arrayOf(
            Number(),
            Space(),
            Arithmetic()
        ),
        tokenConsumer = ArithmeticConsumer()
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
}
