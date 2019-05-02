package com.er453r.ktave.lang

import com.er453r.ktave.parser.Token
import com.er453r.ktave.parser.TokenConsumer
import mu.KotlinLogging

class ArithmeticConsumer : TokenConsumer, Expression {
    private val log = KotlinLogging.logger {}

    private var current: Expression? = null

    override fun addToken(token: Token) {
        log.info { "New ${token::class.simpleName} appeared" }

        if(token is Space)
            return

        if (token is ExpressionConsumer && token.isAccepting && !(current != null && token.hasPrecedenceOver(current!!))) {
            log.info { "New consumer appeared" }

            token.addExpression(current)
            this.current = token

            return
        }

        if (current == null && token is Expression) {
            log.info { "Adding node" }

            current = token

            return
        }

        current?.let {
            if (it is ExpressionConsumer && it.isAccepting && token is Expression) {
                log.info { "Adding to existing consumer" }

                it.addExpression(token)

                return
            }
        }

        throw Exception("Do not know what to do with $token")
    }

    override val value: Double
        get() = current!!.value
}
