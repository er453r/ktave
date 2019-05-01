package com.er453r.ktave

import com.er453r.ktave.lang.Arithmetic
import com.er453r.ktave.lang.ArithmeticConsumer
import com.er453r.ktave.lang.Number
import com.er453r.ktave.lang.Space
import mu.KotlinLogging
import kotlin.test.Test
import kotlin.test.assertEquals

class ParserBinOpsTests {
    private val log = KotlinLogging.logger {}

    private fun eval(statement: String): Double {
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

        return result
    }

    @Test
    fun `Simple addition test`() = assertEquals(5.0, eval("3 + 2"))

    @Test
    fun `Simple subtraction test`() = assertEquals(2.0, eval("3 -1"))

    @Test
    fun `Simple multiplication test`() = assertEquals(6.0, eval("3* 2"))

    @Test
    fun `Simple division test`() = assertEquals(3.0, eval("6 / 2"))

    @Test
    fun `Simple float addition test`() = assertEquals(5.77, eval("3.21 + 2.56"))

    @Test
    fun `Simple float subtraction test`() = assertEquals(0.65, eval("3.21 - 2.56"))

    @Test
    fun `Simple float multiplication test`() = assertEquals(8.0, eval("3.2 * 2.5"))

    @Test
    fun `Simple float division test`() = assertEquals(2.0, eval("3.2 / 1.6"))
}
