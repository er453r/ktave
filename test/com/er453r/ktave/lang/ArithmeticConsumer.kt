package com.er453r.ktave.lang

import com.er453r.ktave.parser.Token
import com.er453r.ktave.parser.TokenConsumer
import mu.KotlinLogging

class ArithmeticConsumer : ExpressionConsumer, TokenConsumer {
    private val log = KotlinLogging.logger {}

    private var current: Expression? = null

    override val value: Double
        get() = current?.value ?: throw Exception("No expression present!")

    override val isAccepting: Boolean
        get() = current?.let {
            when (it) {
                is ExpressionConsumer -> it.isAccepting
                else -> false
            }
        } ?: true

    override fun addToken(token: Token) {
        log.info { "New ${token::class.simpleName} appeared" }

        when (token) {
            is Space -> return
            is Expression -> addExpression(token)
            else -> throw Exception("Do not know what to do with token $token")
        }
    }

    override fun addExpression(expression: Expression) {
        current.let {
            when {
                it == null -> current = expression
                expression is ExpressionConsumer && expression.isAccepting -> {
                    log.info { "New consumer appeared" }

                    expression.addExpression(it)
                    this.current = expression
                }
                it is ExpressionConsumer && it.isAccepting -> {
                    log.info { "Adding to existing consumer" }

                    (current as ExpressionConsumer).addExpression(expression)
                }
                else -> throw Exception("Do not know what to do with $expression")
            }
        }
    }
}
