package com.er453r.ktave.lang

interface ExpressionConsumer : Expression {
    val isAccepting: Boolean

    fun addExpression(expression: Expression, isPrepended:Boolean = false)
    fun hasPrecedenceOver(expression: Expression): Boolean = false
}
