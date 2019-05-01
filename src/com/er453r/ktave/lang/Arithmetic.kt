package com.er453r.ktave.lang

import com.er453r.ktave.parser.Token

class Arithmetic(private val type: String = "+") : Token, ExpressionConsumer {
    override val value: Double
        get() = when (type) {
            "+" -> left!!.value + right!!.value
            "-" -> left!!.value - right!!.value
            "*" -> left!!.value * right!!.value
            "/" -> left!!.value / right!!.value
            else -> throw Exception("Do not know how to do $type")
        }

    override fun addExpression(expression: Expression) {
        when {
            left == null -> left = expression
            right == null -> right = expression
            right is ExpressionConsumer -> right?.let {
                if (it is ExpressionConsumer && it.isAccepting)
                    it.addExpression(expression)
            }
            else -> throw Exception("Do not know how to handle $expression")
        }
    }

    private var left: Expression? = null
    private var right: Expression? = null

    override val isAccepting = true
    override val regex = Regex("[+\\-/*]")

    override fun fromString(statement: String) = Arithmetic(statement)
}
