package com.er453r.ktave.lang

interface ExpressionConsumer : Expression {
    val isAccepting: Boolean

    fun addExpression(expression: Expression)
    fun hasPrecedenceOver(expression: Expression):Boolean = false
}
