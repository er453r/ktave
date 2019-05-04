package com.er453r.ktave.lang

import com.er453r.ktave.parser.Token

class Parenthesis(private val type:String = "(") : Token, ExpressionConsumer {
    override val value: Double
        get() = inside!!.value

    override fun addExpression(expression: Expression, isPrepended:Boolean) {
        when{
            inside == null && expression is Parenthesis && expression.type == ")" -> throw Exception("Empty parenthesis!")
            inside is ExpressionConsumer && (inside as ExpressionConsumer).isAccepting -> (inside as ExpressionConsumer).addExpression(expression)
            inside == null -> inside = expression
            else -> throw Exception("Do not know what to do with $expression")
        }
    }

    private var inside: Expression? = null

    override val isAccepting = true
    override val regex = Regex("[()]")

    override fun fromString(statement: String) = Parenthesis()
}
