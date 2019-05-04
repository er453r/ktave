package com.er453r.ktave.lang

import com.er453r.ktave.parser.Token

class Arithmetic(private val type: String = "+") : Token, ExpressionConsumer {
    companion object {
        private const val PRECEDENCE_ORDER = "*/+-"
    }

    override val value: Double
        get() = when (type) {
            "+" -> (left?.value ?: 0.0) + right!!.value
            "-" -> (left?.value ?: 0.0) - right!!.value
            "*" -> left!!.value * right!!.value
            "/" -> left!!.value / right!!.value
            else -> throw Exception("Do not know how to do $type")
        }

    override fun addExpression(expression: Expression, isPrepended: Boolean) {
        when {
            left == null && isPrepended -> left = expression
            left == null && !isPrepended && (type == "*" || type == "/") -> throw Exception("Multiply/divide need 2 arguments")
            right == null -> right = expression
            right is ExpressionConsumer -> right?.let {
                if (it is ExpressionConsumer && it.isAccepting)
                    it.addExpression(expression)
            }
            expression is ExpressionConsumer -> right?.let {
                expression.addExpression(it, true)
                right = expression
            }
            else -> throw Exception("Do not know how to handle $expression")
        }
    }

    override fun hasPrecedenceOver(expression: Expression): Boolean {
        if (expression is Arithmetic)
            return PRECEDENCE_ORDER.indexOf(type) < PRECEDENCE_ORDER.indexOf(expression.type)

        return false
    }

    private var left: Expression? = null
    private var right: Expression? = null

    override val isAccepting = true
    override val regex = Regex("[+\\-/*]")

    override fun fromString(statement: String) = Arithmetic(statement)
}
