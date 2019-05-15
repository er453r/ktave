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

    override fun addExpression(expression: Expression, isPrepended:Boolean) {
        current.let {
            when {
                it == null -> {
                    log.info { "(setting as current)" }

                    current = expression
                }
                it is ExpressionConsumer && it.isAccepting && !(expression is ExpressionConsumer && !expression.hasPrecedenceOver(it) && !expression.isAccepting) -> {
                    log.info { "(adding to current consumer)" }

                    (current as ExpressionConsumer).addExpression(expression)
                }
                expression is ExpressionConsumer && expression.isAccepting -> {
                    log.info { "(replacing as current consumer)" }

                    expression.addExpression(it, true)
                    this.current = expression
                }
                else -> throw Exception("Do not know what to do with $expression")
            }
        }
    }
}
