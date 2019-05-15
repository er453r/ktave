package com.er453r.ktave.lang

import com.er453r.ktave.parser.Token

class Parenthesis(private val type: String = "(") : Token, ExpressionConsumer {
    override val value: Double
        get() = inside!!.value

    override fun addExpression(expression: Expression, isPrepended: Boolean) {
        when {
            inside == null && expression is Parenthesis && expression.type == ")" -> throw Exception("Empty parenthesis!")
            inside != null && expression is Parenthesis && expression.type == ")" -> isAccepting = false
            inside == null -> ArithmeticConsumer().let {
                it.addExpression(expression)
                inside = it
            }
            inside is ExpressionConsumer && (inside as ExpressionConsumer).isAccepting -> (inside as ExpressionConsumer).addExpression(expression)
            expression is ExpressionConsumer && expression.isAccepting -> inside!!.addExpression(expression) // inside can't be null here
        }
    }

    private var inside: ExpressionConsumer? = null

    override var isAccepting = true
    override val regex = Regex("[()]")

    override fun fromString(statement: String) = Parenthesis()
}
